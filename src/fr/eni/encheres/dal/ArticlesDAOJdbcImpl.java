package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bll.RetraitsManager;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

public class ArticlesDAOJdbcImpl implements ArticlesDAO {
	
	private static UtilisateursManager utilisateurManager = new UtilisateursManager();
	
	private static CategoriesManager categorieManager = new CategoriesManager();
	
	private static RetraitsManager retraitManager = new RetraitsManager();
	
	//ARTICLES_VENDUS
	private static final String SELECT_ALL_ARTICLES = "SELECT * FROM ARTICLES_VENDUS;";
	
	private static final String SELECT_BY_ETAT = "SELECT * FROM ARTICLES_VENDUS WHERE etat_vente = ?;";
	

	private static final String SELECT_BY_ETAT_VENDEUR = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND etat_vente = ?;";

	private static final String SELECT_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?;";
	
	private static final String INSERT_ARTICLE_VENDU = "INSERT INTO ARTICLES_VENDUS "
													+ "(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente) "
													+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET "
													+ "nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?, no_categorie = ?, etat_vente = ? "
													+ "WHERE no_article = ?;";

	private static final String UPDATE_IMAGE_ARTICLE = "UPDATE ARTICLES_VENDUS SET "
													+ "image = ? "
													+ "WHERE no_article = ?;";
	
	private static final String UPDATE_ETAT_ARTICLE = "UPDATE ARTICLES_VENDUS SET "
													+ "etat_vente = ? "
													+ "WHERE no_article = ?;";
	
	private static final String UPDATE_PRIX_VENTE_ARTICLE = "UPDATE ARTICLES_VENDUS SET "
													+ "prix_vente = ? "
													+ "WHERE no_article = ?;";

	private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?;";


	//ARTICLES_VENDUS
	@Override
	public List<ArticleVendu> selectAllArticles() throws BusinessException {
		List<ArticleVendu> liste = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				liste.add(mapArticle(rs));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ARTICLE_ECHEC);
			throw businessException;
			
		}
		return liste;
	}
	
	@Override
	public List<ArticleVendu> selectArticlesSelonEtat(EtatVente etat) throws BusinessException {
		List<ArticleVendu> liste = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ETAT);
			pstmt.setString(1, etat.toString());
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				liste.add(mapArticle(rs));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ARTICLE_ECHEC);
			throw businessException;
			
		}
		return liste;
	}

	public List<ArticleVendu> selectArticlesSelonEtatEtVendeur(Utilisateur utilisateur, EtatVente etat) throws BusinessException {
		List<ArticleVendu> liste = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ETAT_VENDEUR);
			pstmt.setInt(1, utilisateur.getId());
			pstmt.setString(2, etat.toString());
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				liste.add(mapArticle(rs));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ARTICLE_ECHEC);
			throw businessException;
			
		}
		return liste;
	}

	@Override
	public ArticleVendu selectArticle(int id) throws BusinessException {
		ArticleVendu article = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = mapArticle(rs);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_INEXISTANT);
			throw businessException;
			
		}
		return article;
	}
	
	@Override
	public void insertArticle(ArticleVendu article) throws BusinessException {
		if(article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE_VENDU, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(article.getDateDebutEncheresSQL()));
			pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(article.getDateFinEncheresSQL()));
			pstmt.setInt(5, article.getMiseAPrix());
			pstmt.setInt(6, article.getMiseAPrix());
			pstmt.setInt(7, article.getVendeur().getId());
			pstmt.setInt(8, article.getCategorieArticle().getNoCategorie());
			pstmt.setString(9, article.getEtatVente().toString());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				article.setNoArticle(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
			
		}
		
	}
	
	@Override
	public void updateArticle(ArticleVendu article, ArticleVendu articleAModifier) throws BusinessException {
		if(article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_NULL);
			throw businessException;
			
		}
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE);
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(article.getDateDebutEncheresSQL()));
			pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(article.getDateFinEncheresSQL()));
			pstmt.setInt(5, article.getMiseAPrix());
			pstmt.setInt(6, article.getPrixDeVente());
			pstmt.setInt(7, article.getCategorieArticle().getNoCategorie());
			pstmt.setString(8, article.getEtatVente().toString());
			pstmt.setInt(9, articleAModifier.getNoArticle());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_ECHEC);
			throw businessException;
		}
		
	}

	@Override
	public void updateImageArticle(ArticleVendu article, String image) throws BusinessException {
		if(image == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_NULL);
			throw businessException;
			
		}
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_IMAGE_ARTICLE);
			pstmt.setString(1, image);
			pstmt.setInt(2, article.getNoArticle());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_ECHEC);
			throw businessException;
		}
	}
	
	@Override
	public void updateEtatArticle(ArticleVendu article, EtatVente etat) throws BusinessException {
		if(article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_NULL);
			throw businessException;
			
		}
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ETAT_ARTICLE);
			pstmt.setString(1, etat.toString());
			pstmt.setInt(2, article.getNoArticle());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_ECHEC);
			throw businessException;
			
		}
	}
	
	@Override
	public void updatePrixVenteArticle(ArticleVendu article, int prixDeVente) throws BusinessException {
		if(article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_NULL);
			throw businessException;
			
		}
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_PRIX_VENTE_ARTICLE);
			pstmt.setInt(1, prixDeVente);
			pstmt.setInt(2, article.getNoArticle());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_ECHEC);
			throw businessException;
			
		}
		
	}

	@Override
	public void deleteArticle(int id) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_ARTICLE_ERREUR);
			throw businessException;
		}
		
	}
	
	private ArticleVendu mapArticle(ResultSet rs) throws SQLException, BusinessException {
	
		int noArticle = rs.getInt("no_article");
		String nomArticle = rs.getString("nom_article");
		String description = rs.getString("description");
		LocalDateTime dateDebutEncheres = rs.getTimestamp("date_debut_encheres").toLocalDateTime();
		LocalDateTime dateFinEncheres = rs.getTimestamp("date_fin_encheres").toLocalDateTime();
		int miseAPrix = rs.getInt("prix_initial");
		int prixDeVente = rs.getInt("prix_vente");
		EtatVente etatVente = EtatVente.valueOf(rs.getString("etat_vente"));		
		
		Retrait lieuRetrait = retraitManager.recupererRetrait(noArticle);
		
		Categorie categorieArticle = categorieManager.recupererCategorie(rs.getInt("no_categorie"));
		
		Utilisateur vendeur = null;
		
		List<Utilisateur> liste = utilisateurManager.listerUtilisateurs();
		for(Utilisateur u : liste) {
			if(u.getId() == rs.getInt("no_utilisateur")) {
				vendeur = u;
				break;
			}
		}
		
		String image = rs.getString("image");
	
		return new ArticleVendu(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixDeVente, etatVente, lieuRetrait, categorieArticle, vendeur, image);
	}

}
