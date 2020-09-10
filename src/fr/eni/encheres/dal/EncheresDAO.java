package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public interface EncheresDAO {
	
	//ARTICLES_VENDUS
	public List<Enchere> selectAllEncheres() throws BusinessException;
	
	public List<Enchere> selectEncheresSurArticle(int noArticle) throws BusinessException;
	
	public List<Integer> selectEncheresParUtilisateur(int noUtilisateur) throws BusinessException;
	
	public Enchere selectDerniereEnchereSurArticle(int noArticle) throws BusinessException;
	
	public Enchere selectEncheresGagnees(Utilisateur acheteur, ArticleVendu article) throws BusinessException;

	public void insertEnchere(Enchere enchere) throws BusinessException;


}
