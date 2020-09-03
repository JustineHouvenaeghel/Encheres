package fr.eni.encheres.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Echec le nom de l'article ne respecte pas les règles définies
	 */
	public static final int REGLE_CHAMP_VIDE = 20000;
	
	/**
	 * Echec le nom de l'article ne respecte pas les règles définies
	 */
	public static final int REGLE_CODE_POSTAL_INVALIDE = 20001;
	
	/**
	 * Echec le pseudo n'est pas disponible
	 */
	public static final int REGLE_PSEUDO_INDISPONIBLE = 20002;
	
	/**
	 * Echec l'email n'est pas disponible
	 */
	public static final int REGLE_EMAIL_INDISPONIBLE = 20003;

	/**
	 * Echec le nlogin n'existe pas
	 */
	public static final int REGLE_LOGIN_INEXISTANT = 20004;
	
	/**
	 * Echec le pseudo n'est pas disponible
	 */
	public static final int REGLE_ADRESSE_VIDE = 20005;
	
	/**
	 * Echec le pseudo n'est pas disponible
	 */
	public static final int REGLE_CREDIT_INSUFFISANT = 20006;
	
	

}
