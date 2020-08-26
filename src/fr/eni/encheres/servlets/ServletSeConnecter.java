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
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/seConnecter")
public class ServletSeConnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Compte/seConnecter.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String login = request.getParameter("login");
		String motDePasse = request.getParameter("mot_de_passe");
		
		try {
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			Utilisateur u = utilisateurManager.recupererUtilisateur(login);
			
			if(u != null) {
				this.verifierMotDePasse(u, motDePasse);
				session.setAttribute("utilisateur", u);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/accueil.jsp");
				rd.forward(request, response);
				
			} 
			
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Compte/seConnecter.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	private void verifierMotDePasse(Utilisateur u, String motDePasse) throws BusinessException {
		BusinessException businessException = new BusinessException();
//			String motDePasseCrypte = CryptageMotDePasse.encrypt(motDePasse);
//			System.out.println(motDePasseCrypte);
//			System.out.println(u.getMotDePasse());
		if(!u.getMotDePasse().equals(motDePasse)) {
			businessException.ajouterErreur(CodesResultatServlets.MOT_DE_PASSE_INCORRECT);
			throw businessException;
			
		} 
			
	}
	

}
