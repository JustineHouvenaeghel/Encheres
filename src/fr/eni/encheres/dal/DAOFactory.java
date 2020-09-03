package fr.eni.encheres.dal;

public abstract class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static ArticlesDAO getArticlesDAO() {
		return new ArticlesDAOJdbcImpl();
	}
	
	public static CategoriesDAO getCategoriesDAO() {
		return new CategoriesDAOJdbcImpl();
	}
	
	public static RetraitsDAO getRetraitsDAO() {
		return new RetraitsDAOJdbcImpl();
	}
	
	public static EncheresDAO getEncheresDAO() {
		return new EncheresDAOJdbcImpl();
	}
}
