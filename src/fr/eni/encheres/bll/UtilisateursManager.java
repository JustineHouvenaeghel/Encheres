package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateursManager {

	private UtilisateurDAO utilisateurDAO;
	
	public UtilisateursManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public Utilisateur creerUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		this.validerUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, businessException);
		
		if(businessException.hasErreurs()) {
			throw businessException;
			
		} else {
			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
			
			this.utilisateurDAO.insertUtilisateur(utilisateur);
			
			return utilisateur;
		}

	}
	
	public List<Utilisateur> listerUtilisateurs() throws BusinessException {
		return this.utilisateurDAO.selectAllUtilisateur();
	}
	
	public Utilisateur recupererUtilisateur(String login) throws BusinessException {
		Utilisateur utilisateur = null;
		
		List<Utilisateur> liste = this.utilisateurDAO.selectAllUtilisateur();
		if(liste.size() > 0) {
			for(Utilisateur u : liste) {
				if(u.getPseudo().toLowerCase().equals(login.toLowerCase()) || u.getEmail().equals(login)) {
					utilisateur = u;
					break;
				}
			}
		}
		
		if(utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.REGLE_LOGIN_INEXISTANT);
			throw businessException;
			
		} else {
			return utilisateur;
			
		}
		
	}
	
	public Utilisateur recupererUtilisateurParId(int id) throws BusinessException {
		
		return this.utilisateurDAO.selectById(id);
	}
	
	public Utilisateur modifierUtilisateur(int id, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) throws BusinessException {
		Utilisateur u = new Utilisateur(id, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
			
		this.utilisateurDAO.updateUtilisateur(u);
		
		return u;
	}
	
	public Utilisateur diminuerCreditUtilisateur(Utilisateur u, int credit) throws BusinessException {
		
		if(u.getCredit() < credit) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREDIT_INSUFFISANT);
			throw businessException;
			
		} else {
			int nouveauCredit = u.getCredit() - credit;
			this.utilisateurDAO.updateCreditUtilisateur(u, nouveauCredit);
			
			return this.utilisateurDAO.selectById(u.getId());
			
		}
		
	}
	
	public Utilisateur augmenterCreditUtilisateur(Utilisateur u, int credit) throws BusinessException {
		
		int nouveauCredit = u.getCredit() + credit;
		this.utilisateurDAO.updateCreditUtilisateur(u, nouveauCredit);
		
		return this.utilisateurDAO.selectById(u.getId());
		
	}
	
	public void supprimerUtilisateur(Utilisateur u) throws BusinessException {
		this.utilisateurDAO.deleteUtilisateur(u.getId());
	}

	private void validerUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, BusinessException businessException) {
		if(pseudo == "" || nom == "" || prenom == "" || email == "" || rue == "" || codePostal == "" || ville == "" || motDePasse == "") {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CHAMP_VIDE);
			
		}
		
		if(codePostal.length() != 5) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CODE_POSTAL_INVALIDE);
		}
		
		try {
			List<Utilisateur> liste = this.utilisateurDAO.selectAllUtilisateur();
			for(int i = 0; i < liste.size(); i++) {
				if(pseudo.toLowerCase().equals(liste.get(i).getPseudo().toLowerCase())) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_INDISPONIBLE);
					break;
					
				}
			}
			for(int i = 0; i < liste.size(); i++) {
				if(email.toLowerCase().equals(liste.get(i).getEmail().toLowerCase())) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_INDISPONIBLE);
					break;
					
				}
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public void verifierDisponibiliteEmail(String email) throws BusinessException {
		BusinessException businessException = new BusinessException();
		try {
			List<Utilisateur> liste = this.utilisateurDAO.selectAllUtilisateur();
			
			for(int i = 0; i < liste.size(); i++) {
				if(email.toLowerCase().equals(liste.get(i).getEmail().toLowerCase())) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_INDISPONIBLE);
					break;
					
				}
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public void verifierDisponibilitePseudo(String pseudo) throws BusinessException {
		BusinessException businessException = new BusinessException();
		try {
			List<Utilisateur> liste = this.utilisateurDAO.selectAllUtilisateur();
			for(int i = 0; i < liste.size(); i++) {
				if(pseudo.toLowerCase().equals(liste.get(i).getPseudo().toLowerCase())) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_INDISPONIBLE);
					break;
					
				}
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}
