package fr.eni.encheres.bll;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EncheresDAO;

public class EncheresManager {
	
	private EncheresDAO encheresDAO;
	
	public EncheresManager() {
		encheresDAO = DAOFactory.getEncheresDAO();
		
	}
	
	public void ajouterEnchere(Utilisateur acheteur, ArticleVendu article, LocalDateTime dateEnchere, int montantEnchere) throws BusinessException {
		Enchere enchere = new Enchere(dateEnchere, montantEnchere, article, acheteur);
		this.encheresDAO.insertEnchere(enchere);
		
	}
	
	public Enchere voirDernierEncherisseur(ArticleVendu article) throws BusinessException {
		
		return this.encheresDAO.selectDerniereEnchereSurArticle(article.getNoArticle());
		
	}
	
	public List<Integer> voirEncheresPartUtilisateur(Utilisateur utilisateur) throws BusinessException {
		
		return this.encheresDAO.selectEncheresParUtilisateur(utilisateur.getId());
	}

}
