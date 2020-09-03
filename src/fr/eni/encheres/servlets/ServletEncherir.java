package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletEncherir
 */
@WebServlet("/encherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		int noArticle = Integer.valueOf(request.getParameter("no_article"));
		int montantEnchere = Integer.valueOf(request.getParameter("montant_enchere"));
		LocalDateTime dateEnchere = LocalDateTime.now();
		Utilisateur acheteur = (Utilisateur) session.getAttribute("utilisateur");
		
		try {
			ArticlesManager articleManager = new ArticlesManager();
			EncheresManager encheresManager = new EncheresManager();
			UtilisateursManager utilisateurManager = new UtilisateursManager();
			
			ArticleVendu article = articleManager.recupererArticle(noArticle);
			
			Enchere precedenteEnchere = encheresManager.voirDernierEncherisseur(article);
			
			acheteur = utilisateurManager.diminuerCreditUtilisateur(acheteur, montantEnchere);
			
			if(precedenteEnchere != null) {
				utilisateurManager.augmenterCreditUtilisateur(precedenteEnchere.getAcheteur(), precedenteEnchere.getMontantEnchere());
				
			}
			
			encheresManager.ajouterEnchere(acheteur, article, dateEnchere, montantEnchere);
			articleManager.modifierPrixDeVente(article, montantEnchere);
			
			request.setAttribute("modificationEffectuee", "Votre enchère a bien été ajoutée.");
			
			ArticleVendu a = articleManager.recupererArticle(noArticle);
			
			Enchere enchere = encheresManager.voirDernierEncherisseur(a);
			request.setAttribute("article", a);
			request.setAttribute("enchere", enchere);
			session.setAttribute("utilisateur", acheteur);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());	
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Vente/voirDetailArticle.jsp");
		rd.forward(request, response);
	}

}
