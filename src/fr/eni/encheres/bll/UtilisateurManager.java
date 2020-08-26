package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public Utilisateur creerUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		this.validerUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, businessException);
		
		if(businessException.hasErreurs()) {
			throw businessException;
			
		} else {
			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
			
			this.utilisateurDAO.insert(utilisateur);
			
			return utilisateur;
		}

	}
	
	public List<Utilisateur> listerUtilisateurs() throws BusinessException {
		return this.utilisateurDAO.selectAll();
	}
	
	public Utilisateur recupererUtilisateur(String login) throws BusinessException {
		Utilisateur u = this.utilisateurDAO.selectByEmail(login);
		
		if(u == null) {
			u = this.utilisateurDAO.selectByPseudo(login);
		}
		
		if(u == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.REGLE_LOGIN_INEXISTANT);
			throw businessException;
			
		} else {
			return u;
			
		}
		
	}
	
	public void modifierUtilisateur(int id, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) throws BusinessException {
		Utilisateur u = new Utilisateur(id, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
			
		this.utilisateurDAO.update(u);
	}
	
	public void supprimerUtilisateur(Utilisateur u) throws BusinessException {
		this.utilisateurDAO.delete(u.getId());
	}

	private void validerUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, BusinessException businessException) {
		if(pseudo == null || nom == null || prenom == null || email == null || rue == null || codePostal == null || ville == null || motDePasse == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CHAMP_VIDE);
			
		}
		
		if(codePostal.length() != 5) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CODE_POSTAL_INVALIDE);
		}
		
		try {
			List<Utilisateur> liste = this.utilisateurDAO.selectAll();
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
			List<Utilisateur> liste = this.utilisateurDAO.selectAll();
			
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
			List<Utilisateur> liste = this.utilisateurDAO.selectAll();
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
