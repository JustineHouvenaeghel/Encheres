package fr.eni.encheres.bo;

public class Retrait {

	
	private String rue;
	private String codePostal;
	private String ville;
	private ArticleVendu article;
	
	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
		
	}
	
	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
		
	}
	
	/**
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
		
	}
	
	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
		
	}
	
	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
		
	}
	
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
		
	}

	/**
	 * @return the article
	 */
	public ArticleVendu getArticle() {
		return article;
		
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(ArticleVendu article) {
		this.article = article;
		
	}

	/**
	 * 
	 */
	public Retrait() {
		
	}

	/**
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param article
	 */
	public Retrait(String rue, String codePostal, String ville, ArticleVendu article) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.article = article;
		
	}

	public Retrait(String rue, String codePostal, String ville) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	
}
