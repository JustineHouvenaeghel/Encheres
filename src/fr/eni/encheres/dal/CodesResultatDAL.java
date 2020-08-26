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
	public static final int LECTURE_LISTES_ECHEC = 10002;
	
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
	

}
