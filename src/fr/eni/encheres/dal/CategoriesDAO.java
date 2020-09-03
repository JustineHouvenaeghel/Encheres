package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;

public interface CategoriesDAO {
	
	//CATEGORIES
	public List<Categorie> selectAllCategories() throws BusinessException;
	
	public Categorie selectCategorie(int noCategorie) throws BusinessException;
	
	public void insertCategorie(Categorie categorie) throws BusinessException;
	
	public void updateCategorie(Categorie categorie) throws BusinessException;
	
	public void deleteCategorie(int noCategorie) throws BusinessException;
	
}
