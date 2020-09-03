package fr.eni.encheres.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticlesDAO;
import fr.eni.encheres.dal.DAOFactory;

public class ArticlesManager {
	
	private ArticlesDAO articlesDAO;
	
	public ArticlesManager() {
		articlesDAO = DAOFactory.getArticlesDAO();
		
	}
	
	public ArticleVendu vendreUnArticle(String nomArticle, String description, LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres, int miseAPrix, int prixDeVente, EtatVente etatVente, Categorie categorieArticle, Utilisateur vendeur) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		this.validerArticle(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, etatVente, categorieArticle, vendeur, businessException);
		
		if(businessException.hasErreurs()) {
			throw businessException;
			
		} else {
			ArticleVendu article = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixDeVente, etatVente, categorieArticle, vendeur);
			this.articlesDAO.insertArticle(article);
			
			return article;
			
		}
		
	}
	
	public void ajouterImage(ArticleVendu article, String image) throws BusinessException {
		System.out.println(image);
		this.articlesDAO.updateImageArticle(article, image);
	}
	
	public List<ArticleVendu> listerArticles() throws BusinessException {
		
		this.mettreEtatAJour();		
		
		return this.articlesDAO.selectAllArticles();
	}
	
	public List<ArticleVendu> listerArticlesSelonEtat(EtatVente etat) throws BusinessException {
		
		this.mettreEtatAJour();
		
		List<ArticleVendu> liste = this.articlesDAO.selectArticlesSelonEtat(etat);
		
		return liste;
	}
	
	public List<ArticleVendu> listerArticlesSelonEtatEtVendeur(Utilisateur utilisateur, EtatVente etat) throws BusinessException {
		
		this.mettreEtatAJour();
		List<ArticleVendu> liste = this.articlesDAO.selectArticlesSelonEtatEtVendeur(utilisateur, etat);
		
		return liste;
	}
	
	public List<ArticleVendu> recupererArticles(String recherche, int noCategorie) throws BusinessException {
		List<ArticleVendu> listeArticles = new ArrayList<>();	
		
		List<ArticleVendu> liste = this.listerArticles();
		for(ArticleVendu article : liste) {
			if(recherche != null && !recherche.equals("")) {
				if(article.getNomArticle().toLowerCase().contains(recherche.toLowerCase()) | article.getCategorieArticle().getNoCategorie() == noCategorie) {
					listeArticles.add(article);
					
				}
				
			} else {
				if(article.getCategorieArticle().getNoCategorie() == noCategorie) {
					listeArticles.add(article);
					
				}
			}
		}
		return listeArticles;
	}
	
	public ArticleVendu recupererArticle(int noArticle) throws BusinessException {
		
		return this.articlesDAO.selectArticle(noArticle);
	}
	
	public void modifierPrixDeVente(ArticleVendu article, int montantEnchere) throws BusinessException {
		
		this.articlesDAO.updatePrixVenteArticle(article, montantEnchere);
		
	}
	
	public ArticleVendu modifierArticle(ArticleVendu articleAModifier, String nomArticle, String description, LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres, int miseAPrix, int prixDeVente, Utilisateur vendeur, Categorie categorieArticle, EtatVente etatVente, Retrait lieuRetrait) throws BusinessException {
		ArticleVendu article = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixDeVente, etatVente, lieuRetrait, categorieArticle, vendeur);
		
		this.articlesDAO.updateArticle(article, articleAModifier);
		
		return article;
		
	}
	
	public void supprimerArticle(ArticleVendu articleASupprimer) throws BusinessException {
		this.articlesDAO.deleteArticle(articleASupprimer.getNoArticle());
	}
	
	private void validerArticle(String nomArticle, String description, LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres, int miseAPrix, EtatVente etatVente, Categorie categorieArticle, Utilisateur vendeur, BusinessException businessException) {
		if(nomArticle == "" || description == "" || dateDebutEncheres == null || dateFinEncheres == null || miseAPrix == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CHAMP_VIDE);
			
		}
	}
	
	private void mettreEtatAJour() throws BusinessException {
		List<ArticleVendu> liste = this.articlesDAO.selectAllArticles();
		for(ArticleVendu a : liste) {
			EtatVente etatVente;
			if(a.getDebutEncheres().isAfter(LocalDateTime.now())) {
				etatVente = EtatVente.ND;
			} else if(a.getFinEncheres().isAfter(LocalDateTime.now())) {
				etatVente = EtatVente.EC;
			} else {
				etatVente = EtatVente.TE;
			}
			this.articlesDAO.updateEtatArticle(a, etatVente);
		}
	}
	

}
