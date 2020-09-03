package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;

public interface RetraitsDAO {
	
	//RETRAITS
	public List<Retrait> selectAllRetraits() throws BusinessException;
	
	public Retrait selectRetrait(int noArticle) throws BusinessException;
	
	public void insertRetrait(Retrait retrait) throws BusinessException;
	
	public void updateRetrait(ArticleVendu article, Retrait retrait) throws BusinessException;
	
	public void deleteRetrait(ArticleVendu article) throws BusinessException;
	

}
