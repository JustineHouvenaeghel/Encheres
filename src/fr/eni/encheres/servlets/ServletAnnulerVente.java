package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bo.ArticleVendu;

/**
 * Servlet implementation class ServletAnnulerVente
 */
@WebServlet("/annulerVente")
public class ServletAnnulerVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noArticle = Integer.valueOf(request.getParameter("no_article"));
		
		ArticlesManager articleManager = new ArticlesManager();
		
		try {
			ArticleVendu articleASupprimer = articleManager.recupererArticle(noArticle);
			articleManager.supprimerArticle(articleASupprimer);

			request.setAttribute("modificationEffectuee", "La vente a bien été annulée.");

			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Encheres/accueil.jsp");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
