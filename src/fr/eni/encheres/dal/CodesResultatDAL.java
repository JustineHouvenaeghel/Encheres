package fr.eni.encheres.dal;

public abstract class CodesResultatDAL {

	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL = 10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC = 10001;
	
	/**
	 * Echec de la lecture de la liste d'utilisateurs
	 */
	public static final int LECTURE_LISTES_UTILISATEUR_ECHEC = 10002;
	
	/**
	 * Echec de la lecture d'un utilisateur
	 */
	public static final int LECTURE_UTILISATEUR_ECHEC = 10003;
	
	/**
	 * Utilisateur inexistant
	 */
	public static final int LECTURE_UTILISATEUR_INEXISTANT = 10004;
	
	/**
	 * Erreur à la modification d'un utilisateur
	 */
	public static final int UPDATE_UTILISATEUR_NULL = 10005;
	
	/**
	 * Erreur à la modification d'un utilisateur
	 */
	public static final int UPDATE_UTILISATEUR_ECHEC = 10006;
	
	/**
	 * Erreur à la suppression d'un utilisation
	 */
	public static final int SUPPRESSION_UTILISATEUR_ERREUR = 10007;

	/**
	 * Echec de la lecture d'un utilisateur
	 */
	public static final int LECTURE_ARTICLE_ECHEC = 10008;
	
	/**
	 * Utilisateur inexistant
	 */
	public static final int LECTURE_ARTICLE_INEXISTANT = 10009;
	
	/**
	 * Erreur à la modification d'un utilisateur
	 */
	public static final int UPDATE_ARTICLE_NULL = 10010;
	
	/**
	 * Erreur à la modification d'un utilisateur
	 */
	public static final int UPDATE_ARTICLE_ECHEC = 10011;
	
	/**
	 * Erreur à la suppression d'un utilisation
	 */
	public static final int SUPPRESSION_ARTICLE_ERREUR = 10012;

	/**
	 * Echec de la lecture d'un utilisateur
	 */
	public static final int LECTURE_CATEGORIE_ECHEC = 10013;
	
	/**
	 * Utilisateur inexistant
	 */
	public static final int LECTURE_CATEGORIE_INEXISTANT = 10014;
	
	/**
	 * Erreur à la modification d'un utilisateur
	 */
	public static final int UPDATE_CATEGORIE_NULL = 10015;
	
	/**
	 * Erreur à la modification d'un utilisateur
	 */
	public static final int UPDATE_CATEGORIE_ECHEC = 10016;
	
	/**
	 * Erreur à la suppression d'un utilisation
	 */
	public static final int SUPPRESSION_CATEGORIE_ERREUR = 10017;

	/**
	 * Echec de la lecture d'un utilisateur
	 */
	public static final int LECTURE_RETRAIT_ECHEC = 10018;
	
	/**
	 * Utilisateur inexistant
	 */
	public static final int LECTURE_RETRAIT_INEXISTANT = 10019;
	
	/**
	 * Erreur à la modification d'un utilisateur
	 */
	public static final int UPDATE_RETRAIT_NULL = 10020;
	
	/**
	 * Erreur à la modification d'un utilisateur
	 */
	public static final int UPDATE_RETRAIT_ECHEC = 10021;
	
	/**
	 * Erreur à la suppression d'un utilisation
	 */
	public static final int SUPPRESSION_RETRAIT_ERREUR = 10022;
	
	/**
	 * Echec de la lecture de la liste d'utilisateurs
	 */
	public static final int LECTURE_LISTES_ARTICLE_ECHEC = 10023;
	
	/**
	 * Echec de la lecture de la liste d'utilisateurs
	 */
	public static final int LECTURE_LISTES_CATEGORIE_ECHEC = 10024;
	
	/**
	 * Echec de la lecture de la liste d'utilisateurs
	 */
	public static final int LECTURE_LISTES_RETRAIT_ECHEC = 10025;

	/**
	 * Echec de la lecture d'un utilisateur
	 */
	public static final int LECTURE_ENCHERE_ECHEC = 10026;
	
	/**
	 * Utilisateur inexistant
	 */
	public static final int LECTURE_ENCHERE_INEXISTANT = 10027;
	
	/**
	 * Echec de la lecture de la liste d'utilisateurs
	 */
	public static final int LECTURE_LISTES_ENCHERE_ECHEC = 10028;

}
