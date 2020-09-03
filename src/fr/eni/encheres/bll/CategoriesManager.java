package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategoriesDAO;
import fr.eni.encheres.dal.DAOFactory;

public class CategoriesManager {
	
	private CategoriesDAO categoriesDAO;
	
	public CategoriesManager() {
		categoriesDAO = DAOFactory.getCategoriesDAO();
		
	}
	
	public List<Categorie> listerCategories() throws BusinessException {
		return this.categoriesDAO.selectAllCategories();
	}
	
	public Categorie recupererCategorie(int noCategorie) throws BusinessException {
		Categorie categorie = null;
		
		List<Categorie> liste = this.categoriesDAO.selectAllCategories();
		for(Categorie c : liste) {
			if(c.getNoCategorie() == noCategorie) {
				categorie = c;
				break;
			}
		}
		
		return categorie;
	}	

}
