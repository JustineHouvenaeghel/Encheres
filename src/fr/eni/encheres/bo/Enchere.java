package fr.eni.encheres.bo;

import java.time.LocalDateTime;

public class Enchere {
	
	private int noEnchere;
	private LocalDateTime dateEnchere;
	private int montantEnchere;
	private ArticleVendu articleVendu;
	private Utilisateur acheteur;
	
	/**
	 * @return the noEnchere
	 */
	public int getNoEnchere() {
		return noEnchere;
	}

	/**
	 * @param noEnchere the noEnchere to set
	 */
	public void setNoEnchere(int noEnchere) {
		this.noEnchere = noEnchere;
	}

	/**
	 * @return the dateEnchere
	 */
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
		
	}
	
	/**
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
		
	}
	
	/**
	 * @return the montantEnchere
	 */
	public int getMontantEnchere() {
		return montantEnchere;
		
	}
	
	/**
	 * @param montantEnchere the montantEnchere to set
	 */
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
		
	}
	
	/**
	 * @return the articleVendu
	 */
	public ArticleVendu getArticleVendu() {
		return articleVendu;
		
	}

	/**
	 * @param articleVendu the articleVendu to set
	 */
	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
		
	}

	/**
	 * @return the acheteur
	 */
	public Utilisateur getAcheteur() {
		return acheteur;
	}

	/**
	 * @param acheteur the acheteur to set
	 */
	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}

	public Enchere() {
		
	}

	/**
	 * @param dateEnchere
	 * @param montantEnchere
	 */
	public Enchere(int noEnchere, LocalDateTime dateEnchere, int montantEnchere, ArticleVendu articleVendu, Utilisateur acheteur) {
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.articleVendu = articleVendu;
		this.acheteur = acheteur;
	}
	
	/**
	 * @param dateEnchere
	 * @param montantEnchere
	 */
	public Enchere(LocalDateTime dateEnchere, int montantEnchere, ArticleVendu articleVendu, Utilisateur acheteur) {
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.articleVendu = articleVendu;
		this.acheteur = acheteur;
	}
	
	
}
