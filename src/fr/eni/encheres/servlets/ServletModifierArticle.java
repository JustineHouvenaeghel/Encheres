package fr.eni.encheres.servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bll.RetraitsManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletModifierArticle
 */

@MultipartConfig

@WebServlet("/modifierArticle")
public class ServletModifierArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String pathImages = "C:\\Users\\jhouvena2020\\Documents\\Eclipse\\ProjetEncheres\\WebContent\\";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int noArticle = Integer.valueOf(request.getParameter("no_article"));
		
		try {
			CategoriesManager categorieManager = new CategoriesManager();
			ArticlesManager articleManager = new ArticlesManager();
			ArticleVendu article = articleManager.recupererArticle(noArticle);
			
			List<Categorie> liste = categorieManager.listerCategories();
			request.setAttribute("listeCategories", liste);
			
			request.setAttribute("article", article);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Vente/modifierArticle.jsp");
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
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		ArticlesManager articleManager = new ArticlesManager();
		CategoriesManager categorieManager = new CategoriesManager();
		RetraitsManager retraitManager = new RetraitsManager();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		
		int noArticle = Integer.valueOf(request.getParameter("no_article"));
		
		String nomArticle = request.getParameter("nom_article");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");
		int noCategorie = 0;
		
		if(categorie != null && !categorie.equals("")) {
			noCategorie = Integer.valueOf(request.getParameter("categorie"));
			
		} 
		
		
		Part imagePart =  request.getPart("image");
		
		String prixInitial = request.getParameter("prix");
		int miseAPrix = 0;
		if(prixInitial != null && !prixInitial.equals("")) {
			miseAPrix = Integer.valueOf(request.getParameter("prix"));
			
		}
		String debut = request.getParameter("debut_enchere");
		String fin =  request.getParameter("fin_enchere");
		LocalDateTime dateDebutEncheres = LocalDateTime.parse(debut, formatter);
		LocalDateTime dateFinEncheres = LocalDateTime.parse(fin, formatter);
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		
		EtatVente etatVente;
		if(dateDebutEncheres.isAfter(LocalDateTime.now())) {
			etatVente = EtatVente.ND;
			
		} else if(dateFinEncheres.isAfter(LocalDateTime.now())) {
			etatVente = EtatVente.EC;
			
		} else {
			etatVente = EtatVente.TE;
			
		}
		
		try {
			ArticleVendu article = articleManager.recupererArticle(noArticle);
			
			Categorie categorieArticle;
			if(noCategorie != 0) {
				categorieArticle = categorieManager.recupererCategorie(noCategorie);
				
			} else {
				categorieArticle = categorieManager.recupererCategorie(article.getCategorieArticle().getNoCategorie());
			
			}
			
			if(miseAPrix == 0 ) {
				miseAPrix = article.getMiseAPrix();
			}
			Retrait lieuRetrait = retraitManager.recupererRetrait(noArticle);
			Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");
			
			ArticleVendu articleModifie = articleManager.modifierArticle(article, nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, miseAPrix, vendeur, categorieArticle, etatVente, lieuRetrait);
			
			if(imagePart.toString().toLowerCase().contains(".jpg") || imagePart.toString().toLowerCase().contains(".png")) {
				System.out.println(imagePart);
				String nomImage = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
				String pathImage = pathImages + "images\\" + article.getNoArticle() + nomImage;
				File imageFile = new File(pathImage);
				InputStream input = imagePart.getInputStream();
				try(OutputStream outputStream = new FileOutputStream(imageFile)){
				    IOUtils.copy(input, outputStream);
				    articleManager.ajouterImage(article, "images/" + imageFile.getName());
				    
				} catch (FileNotFoundException e) {
				    // handle exception here
				} catch (IOException e) {
				    // handle exception here
				}
			} else {
				articleManager.ajouterImage(articleModifie, article.getImage());
			}
			
			Retrait retrait = retraitManager.recupererRetrait(noArticle);
			if(!retrait.getRue().toLowerCase().equals(rue.toLowerCase()) | !retrait.getCodePostal().equals(codePostal) | !retrait.getVille().toLowerCase().equals(ville.toLowerCase())) {
				Retrait nouveauRetrait = new Retrait(rue, codePostal, ville);
				retraitManager.modifierRetrait(articleModifie, nouveauRetrait);
			}
			
			request.setAttribute("article", articleModifie);
			request.setAttribute("modificationEffectuee", "Votre article a bien été modifié.");

			response.sendRedirect(request.getContextPath() + "/accueil");
			
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
	}

}
