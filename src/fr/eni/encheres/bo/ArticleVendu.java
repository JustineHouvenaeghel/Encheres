package fr.eni.encheres.bo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArticleVendu {
	
	private static DateTimeFormatter formatSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private int miseAPrix;
	private int prixDeVente;
	private EtatVente etatVente;
	private Retrait lieuRetrait;
	private Categorie categorieArticle;
	private Utilisateur vendeur;
	private String image = "";

	
	/**
	 * @return the noArticle
	 */
	public int getNoArticle() {
		return noArticle;
		
	}
	
	/**
	 * @param noArticle the noArticle to set
	 */
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
		
	}
	
	/**
	 * @return the nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
		
	}
	
	/**
	 * @param nomArticle the nomArticle to set
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
		
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
		
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
		
	}
	
	/**
	 * @return the dateDebutEncheres
	 */
	public String getDateDebutEncheres() {
		String formatDateDebutEncheres = dateDebutEncheres.format(formatter);
		return formatDateDebutEncheres;
		
	}
	
	/**
	 * @param dateDebutEncheres the dateDebutEncheres to set
	 */
	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
		
	}
	
	public String getDateDebutEncheresSQL() {
		String formatDateDebutEncheres = dateDebutEncheres.format(formatSQL);
		return formatDateDebutEncheres;
	}
	
	public LocalDateTime getDebutEncheres() {
		return this.dateDebutEncheres;
	}
	
	/**
	 * @return the dateFinEncheres
	 */
	public String getDateFinEncheres() {
		String formatDateFinEncheres = dateFinEncheres.format(formatter);
		return formatDateFinEncheres;
		
	}
	
	/**
	 * @param dateFinEncheres the dateFinEncheres to set
	 */
	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
		
	}
	
	public String getDateFinEncheresSQL() {
		String formatDateFinEncheres = dateFinEncheres.format(formatSQL);
		return formatDateFinEncheres;
	}
	
	public LocalDateTime getFinEncheres() {
		return this.dateFinEncheres;
	}
	
	/**
	 * @return the miseAPrix
	 */
	public int getMiseAPrix() {
		return miseAPrix;
		
	}
	
	/**
	 * @param miseAPrix the miseAPrix to set
	 */
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	
	}
	
	/**
	 * @return the prixDeVente
	 */
	public int getPrixDeVente() {
		return prixDeVente;
	}

	/**
	 * @param prixDeVente the prixDeVente to set
	 */
	public void setPrixDeVente(int prixDeVente) {
		this.prixDeVente = prixDeVente;
	}

	/**
	 * @return the etatVente
	 */
	public EtatVente getEtatVente() {
		return etatVente;
	}

	/**
	 * @param etatVente the etatVente to set
	 */
	public void setEtatVente(EtatVente etatVente) {
		this.etatVente = etatVente;
	}

	/**
	 * @return the lieuRetrait
	 */
	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}

	/**
	 * @param lieuRetrait the lieuRetrait to set
	 */
	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	/**
	 * @return the categorieArticle
	 */
	public Categorie getCategorieArticle() {
		return categorieArticle;
		
	}

	/**
	 * @param categorieArticle the categorieArticle to set
	 */
	public void setCategorieArticle(Categorie categorieArticle) {
		this.categorieArticle = categorieArticle;
		
	}

	/**
	 * @return the vendeur
	 */
	public Utilisateur getVendeur() {
		return vendeur;
		
	}

	/**
	 * @param vendeur the vendeur to set
	 */
	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
		
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 
	 */
	public ArticleVendu() {
		
	}

	/**
	 * 
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixDeVente
	 * @param etatVente
	 * @param lieuRetrait
	 * @param categorieArticle
	 * @param vendeur
	 */
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixDeVente, EtatVente etatVente, Retrait lieuRetrait, Categorie categorieArticle,
			Utilisateur vendeur) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixDeVente = prixDeVente;
		this.etatVente = etatVente;
		this.lieuRetrait = lieuRetrait;
		this.categorieArticle = categorieArticle;
		this.vendeur = vendeur;
		
	}

	/**
	 * 
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixDeVente
	 * @param etatVente
	 * @param categorieArticle
	 * @param vendeur
	 */
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixDeVente, EtatVente etatVente, Categorie categorieArticle,
			Utilisateur vendeur) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixDeVente = prixDeVente;
		this.etatVente = etatVente;
		this.categorieArticle = categorieArticle;
		this.vendeur = vendeur;
	}

	/**
	 * 
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixDeVente
	 * @param etatVente
	 * @param lieuRetrait
	 * @param categorieArticle
	 * @param vendeur
	 */
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixDeVente, EtatVente etatVente, Retrait lieuRetrait,
			Categorie categorieArticle, Utilisateur vendeur) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixDeVente = prixDeVente;
		this.etatVente = etatVente;
		this.lieuRetrait = lieuRetrait;
		this.categorieArticle = categorieArticle;
		this.vendeur = vendeur;
	}

	/**
	 * 
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixDeVente
	 * @param etatVente
	 * @param lieuRetrait
	 * @param categorieArticle
	 * @param vendeur
	 * @param image
	 */
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixDeVente, EtatVente etatVente, Retrait lieuRetrait,
			Categorie categorieArticle, Utilisateur vendeur, String image) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixDeVente = prixDeVente;
		this.etatVente = etatVente;
		this.lieuRetrait = lieuRetrait;
		this.categorieArticle = categorieArticle;
		this.vendeur = vendeur;
		this.image = image;
		
	}
	
	
}
