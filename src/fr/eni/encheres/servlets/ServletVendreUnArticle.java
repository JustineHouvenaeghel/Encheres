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
 * Servlet implementation class ServletVendreUnArticle
 */

@MultipartConfig

@WebServlet("/vendreUnArticle")
public class ServletVendreUnArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String pathImages = "C:\\Users\\jhouvena2020\\Documents\\Eclipse\\ProjetEncheres\\WebContent\\";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoriesManager categorieManager = new CategoriesManager();
		
		try {
			List<Categorie> liste = categorieManager.listerCategories();
			request.setAttribute("listeCategories", liste);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());	
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Vente/vendreUnArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		CategoriesManager categorieManager = new CategoriesManager();
		RetraitsManager retraitManager = new RetraitsManager();
		
		try {
			List<Categorie> liste = categorieManager.listerCategories();
			request.setAttribute("listeCategories", liste);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		
		String nomArticle = request.getParameter("nom_article");
		String description = request.getParameter("description");
		int categorie = Integer.valueOf(request.getParameter("categorie"));
		
		Part imagePart =  request.getPart("image");
		
		int miseAPrix = Integer.valueOf(request.getParameter("prix"));
		LocalDateTime dateDebutEncheres = LocalDateTime.parse(request.getParameter("debut_enchere"), formatter);
		LocalDateTime dateFinEncheres = LocalDateTime.parse(request.getParameter("fin_enchere"), formatter);
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");
		
		EtatVente etatVente;
		if(dateDebutEncheres.isAfter(LocalDateTime.now())) {
			etatVente = EtatVente.ND;
			
		} else if(dateFinEncheres.isAfter(LocalDateTime.now())) {
			etatVente = EtatVente.EC;
			
		} else {
			etatVente = EtatVente.TE;
			
		}
		
		try {
			ArticlesManager articleManager = new ArticlesManager();
			Categorie categorieArticle = categorieManager.recupererCategorie(categorie);
			
			ArticleVendu article = articleManager.vendreUnArticle(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, miseAPrix, etatVente, categorieArticle, vendeur);
			
			if(imagePart != null) {
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
			}
			
			Retrait retrait = new Retrait(rue, codePostal, ville, article);
			
			retraitManager.ajouterRetrait(retrait);
			
			request.setAttribute("modificationEffectuee", "Votre article a bien été ajouté.");
			
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());	
			
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Vente/vendreUnArticle.jsp");
		rd.forward(request, response);
		
	}

}
