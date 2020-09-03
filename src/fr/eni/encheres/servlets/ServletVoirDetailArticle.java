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
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;

/**
 * Servlet implementation class ServletVoirProfil
 */
@WebServlet("/voirDetailArticle")
public class ServletVoirDetailArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		int noArticle = Integer.valueOf(request.getParameter("no_article"));
		
		try {
			EncheresManager encheresManager = new EncheresManager();
			ArticlesManager articleManager = new ArticlesManager();
			
			ArticleVendu a = articleManager.recupererArticle(noArticle);
			Enchere enchere = encheresManager.voirDernierEncherisseur(a);
			
			request.setAttribute("article", a);
			request.setAttribute("enchere", enchere);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Vente/voirDetailArticle.jsp");
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
