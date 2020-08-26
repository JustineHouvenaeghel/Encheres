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
import fr.eni.encheres.bll.UtilisateurManager;
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
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
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
		String confirmationMotDePasse = request.getParameter("mot_de_passe_2");
		
		Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
		
		if(u.getMotDePasse().equals(motDePasseActuel)) {
			if(!u.getPseudo().equals(pseudo)) {
				try {
					utilisateurManager.verifierDisponibilitePseudo(pseudo);
					
				} catch (BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				}
			}
			
			if(!u.getEmail().equals(email)) {
				try {
					utilisateurManager.verifierDisponibiliteEmail(email);
				} catch (BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
					
				}
			}
			
			if(nouveauMotDePasse == null || nouveauMotDePasse.equals("")) {
				try {
					Utilisateur utilisateur = new Utilisateur(u.getId(), pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasseActuel);
					System.out.println(utilisateur.toString());
					utilisateurManager.modifierUtilisateur(u.getId(), pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasseActuel);
				
					session.setAttribute("utilisateur", utilisateur);
					request.setAttribute("modificationEffectuee", "Les modifications ont bien été effectuée");
					
				} catch (BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
					
				}
			} else {
				try {
					verifierMotDePasse(nouveauMotDePasse, confirmationMotDePasse);
					Utilisateur utilisateur = new Utilisateur(u.getId(), pseudo, nom, prenom, email, telephone, rue, codePostal, ville, nouveauMotDePasse);
					utilisateurManager.modifierUtilisateur(u.getId(), pseudo, nom, prenom, email, telephone, rue, codePostal, ville, nouveauMotDePasse);
				
					session.setAttribute("utilisateur", utilisateur);
					request.setAttribute("modificationEffectuee", "Les modifications ont bien été effectuée");
					
				} catch (BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
					
				};
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Compte/modifierProfil.jsp");
		rd.forward(request, response);
		
	}
	
	private void verifierMotDePasse(String nouveauMotDePasse, String confirmationMotDePasse) throws BusinessException {
		BusinessException businessException = new BusinessException();
		if(!nouveauMotDePasse.equals(confirmationMotDePasse)) {
			businessException.ajouterErreur(CodesResultatServlets.MOT_DE_PASSE_INCORRECT);
			throw businessException;
			
		} 
			
	}
}
