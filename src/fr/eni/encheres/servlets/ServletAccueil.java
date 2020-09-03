package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet(
		urlPatterns = {
				"",
				"/accueil"
		})
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		CategoriesManager categorieManager = new CategoriesManager();
		ArticlesManager articleManager = new ArticlesManager();
		
		List<ArticleVendu> listeArticles = new ArrayList<>();
		
		try {
			List<Categorie> listeCategories;
			List<ArticleVendu> liste = new ArrayList<>();
			listeCategories = categorieManager.listerCategories();
			request.setAttribute("listeCategories", listeCategories);
		
		
			String recherche = request.getParameter("recherche");
			String categorieRecherche = request.getParameter("categorie");
			int noCategorie = 0;
			if(categorieRecherche != null) {
				if(!categorieRecherche.equals("")) {
					noCategorie = Integer.valueOf(categorieRecherche);
				}
			}
			
			if(session.getAttribute("utilisateur") != null) {
				Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
				String filtreRecherche = request.getParameter("filtre_enchere");
					if(filtreRecherche != null) {
						
						
						if(filtreRecherche.toLowerCase().equals("achat")) {
							if(request.getParameter("encheres_ouvertes") != null) {
								listeArticles = articleManager.listerArticlesSelonEtat(EtatVente.EC);
								//TODO
							}
							
						} else {
							if(request.getParameter("mes_ventes_en_cours") != null) {
								liste = articleManager.listerArticlesSelonEtatEtVendeur(u, EtatVente.EC);
								for(ArticleVendu a : liste) {
									if(!listeArticles.contains(a)) {
										listeArticles.add(a);
									}
								}
								
							}
							
							if(request.getParameter("ventes_non_débutees") != null) {
								liste = articleManager.listerArticlesSelonEtatEtVendeur(u, EtatVente.ND);
								for(ArticleVendu a : liste) {
									if(!listeArticles.contains(a)) {
										listeArticles.add(a);
									}
								}
							}
							
							if(request.getParameter("ventes_terminees") != null) {
								liste = articleManager.listerArticlesSelonEtatEtVendeur(u, EtatVente.TE);
								for(ArticleVendu a : liste) {
									if(!listeArticles.contains(a)) {
										listeArticles.add(a);
									}
								}
							}
						}
					}
			}
					
			if(recherche != null || noCategorie != 0) {
				if(!recherche.equals("")) {
					
							
					} else {
						liste = articleManager.recupererArticles(recherche, noCategorie);			
						for(ArticleVendu a : liste) {
							if(!listeArticles.contains(a)) {
								listeArticles.add(a);
							}
						}
						
					}
				
					
			} else {
				liste = articleManager.listerArticlesSelonEtat(EtatVente.EC);
				for(ArticleVendu a : liste) {
					if(!listeArticles.contains(a)) {
						listeArticles.add(a);
					}
				}
				
			}
	
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());	
		}
				

		request.setAttribute("listeArticles", listeArticles);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
