package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Utilisateur;

public interface ArticlesDAO {
	
	//ARTICLES_VENDUS
	public List<ArticleVendu> selectAllArticles() throws BusinessException;
	
	public List<ArticleVendu> selectArticlesSelonEtat(EtatVente etat) throws BusinessException;

	public List<ArticleVendu> selectArticlesSelonEtatEtVendeur(Utilisateur utilisateur, EtatVente etat) throws BusinessException;
	
	public ArticleVendu selectArticle(int id) throws BusinessException;

	public void insertArticle(ArticleVendu article) throws BusinessException;
	
	public void updateArticle(ArticleVendu article, ArticleVendu articleAModifier) throws BusinessException;
	
	public void updateImageArticle(ArticleVendu article, String image) throws BusinessException;
	
	public void updateEtatArticle(ArticleVendu article, EtatVente etat) throws BusinessException;
	
	public void updatePrixVenteArticle(ArticleVendu article, int prixDeVente) throws BusinessException;
	
	public void deleteArticle(int id) throws BusinessException;	

}
