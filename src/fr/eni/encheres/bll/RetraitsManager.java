package fr.eni.encheres.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.RetraitsDAO;

public class RetraitsManager {
	
	private RetraitsDAO retraitsDAO;
	
	public RetraitsManager() {
		retraitsDAO = DAOFactory.getRetraitsDAO();
		
	}
	
	
	public void ajouterRetrait(Retrait retrait) throws BusinessException {
		this.retraitsDAO.insertRetrait(retrait);
	}
	
	public Retrait recupererRetrait(int noArticle) throws BusinessException {
		
		return this.retraitsDAO.selectRetrait(noArticle);
	}
	public void modifierRetrait(ArticleVendu article, Retrait retrait) throws BusinessException {
		
		this.retraitsDAO.updateRetrait(article, retrait);
	}
	
}
