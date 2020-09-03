package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.CryptageMotDePasse;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet("/modifierProfil")
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Compte/modifierProfil.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UtilisateursManager utilisateurManager = new UtilisateursManager();
		BusinessException businessException = new BusinessException();
		
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		String motDePasseActuel = request.getParameter("mot_de_passe_actuel");
		String nouveauMotDePasse = request.getParameter("mot_de_passe");
		
		Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
		
		try {
			if(verifierMotDePasse(u, motDePasseActuel)) {
				if(!u.getPseudo().equals(pseudo)) {
					utilisateurManager.verifierDisponibilitePseudo(pseudo);
					
				} 
			
				if(!u.getEmail().equals(email)) {
						utilisateurManager.verifierDisponibiliteEmail(email);
					
				}
				
				if(nouveauMotDePasse == null || nouveauMotDePasse.equals("")) {
						Utilisateur utilisateur = new Utilisateur(u.getId(), pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasseActuel);
						System.out.println(utilisateur.toString());
						utilisateurManager.modifierUtilisateur(u.getId(), pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasseActuel);
					
						session.setAttribute("utilisateur", utilisateur);
						request.setAttribute("modificationEffectuee", "Les modifications ont bien été effectuée");
						
					
				} else {
					String motDePasseCrypte = CryptageMotDePasse.hash(nouveauMotDePasse);
					Utilisateur utilisateur = utilisateurManager.modifierUtilisateur(u.getId(), pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasseCrypte);					
					
					session.setAttribute("utilisateur", utilisateur);
					request.setAttribute("modificationEffectuee", "Les modifications ont bien été effectuée");
					
				} 
			} else {
					businessException.ajouterErreur(CodesResultatServlets.MOT_DE_PASSE_INCORRECT);
					throw businessException;
			}
				
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			
		}
			
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Compte/modifierProfil.jsp");
		rd.forward(request, response);
		
	}
	
	private boolean verifierMotDePasse(Utilisateur u, String motDePasse) throws BusinessException {
		return CryptageMotDePasse.verifyHash(motDePasse, u.getMotDePasse());		
			
	}
	
}
