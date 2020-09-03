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
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/creerUnCompte")
public class ServletCreerUnCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Encheres/Compte/creerUnCompte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("mot_de_passe");
		
		try {
			String motDePasseCrypte = CryptageMotDePasse.hash(motDePasse);
			
			UtilisateursManager utilisateurManager = new UtilisateursManager();
			Utilisateur utilisateur;
			utilisateur = utilisateurManager.creerUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasseCrypte);
			session.setAttribute("utilisateur", utilisateur);

			response.sendRedirect(request.getContextPath() + "/accueil");
			
		} catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Compte/creerUnCompte.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
}
