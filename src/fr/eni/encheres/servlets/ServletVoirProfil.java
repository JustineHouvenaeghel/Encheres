package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletVoirProfil
 */
@WebServlet("/voirProfil")
public class ServletVoirProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pseudo = request.getParameter("pseudo");
		
		try {
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			Utilisateur u = utilisateurManager.recupererUtilisateur(pseudo);
			
			request.setAttribute("pseudo", u.getPseudo());
			request.setAttribute("nom", u.getNom());
			request.setAttribute("prenom", u.getPrenom());
			request.setAttribute("email", u.getEmail());
			request.setAttribute("telephone", u.getTelephone());
			request.setAttribute("rue", u.getRue());
			request.setAttribute("codePostal", u.getCodePostal());
			request.setAttribute("ville", u.getVille());
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Compte/voirProfil.jsp");
			rd.forward(request, response);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
