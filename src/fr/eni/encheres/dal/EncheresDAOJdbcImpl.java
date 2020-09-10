package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public class EncheresDAOJdbcImpl implements EncheresDAO {
	
	private static ArticlesManager articleManager = new ArticlesManager();
	
	private static UtilisateursManager utilisateurManager = new UtilisateursManager();
	
	private static final String SELECT_ALL_ENCHERES = "SELECT * FROM ENCHERES;";
	
	private static final String SELECT_ENCHERES_BY_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article = ?;";
	
	private static final String SELECT_ENCHERES_BY_UTILISATEUR = "SELECT DISTINCT no_article FROM ENCHERES WHERE no_utilisateur = ?;";
	
	private static final String SELECT_MAX_ENCHERE = "SELECT no_enchere, no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES "
													+ "WHERE montant_enchere = (SELECT MAX(montant_enchere) FROM ENCHERES) AND no_article = ?;";
	
	private static final String SELECT_ENCHERES_GAGNEES_PAR_UTILISATEUR = "SELECT no_enchere, no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES "
													+ "WHERE montant_enchere = (SELECT MAX(montant_enchere) FROM ENCHERES) AND no_article = ? AND no_utilisateur = ?;";
	
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?);";

	@Override
	public List<Enchere> selectAllEncheres() throws BusinessException {
		List<Enchere> liste = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ENCHERES);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				liste.add(mapEnchere(rs));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ENCHERE_ECHEC);
			throw businessException;
			
		}
		return liste;
	}
	
	@Override
	public List<Enchere> selectEncheresSurArticle(int noArticle) throws BusinessException {
		List<Enchere> liste = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERES_BY_ARTICLE);
			pstmt.setInt(1, noArticle);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				liste.add(mapEnchere(rs));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ENCHERE_ECHEC);
			throw businessException;
			
		}
		return liste;
	}
	
	@Override
	public List<Integer> selectEncheresParUtilisateur(int noUtilisateur) throws BusinessException {
		List<Integer> liste = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERES_BY_UTILISATEUR);
			pstmt.setInt(1, noUtilisateur);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				liste.add(rs.getInt(1));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ENCHERE_ECHEC);
			throw businessException;
			
		}
		return liste;
	}

	@Override
	public Enchere selectDerniereEnchereSurArticle(int noArticle) throws BusinessException {
		Enchere enchere = null;
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_MAX_ENCHERE);
			pstmt.setInt(1, noArticle);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {

				enchere = mapEnchere(rs);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ENCHERE_ECHEC);
			throw businessException;
			
		}
		return enchere;
	}
	
	@Override
	public Enchere selectEncheresGagnees(Utilisateur acheteur, ArticleVendu article) throws BusinessException {
		Enchere enchereRemportee = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERES_GAGNEES_PAR_UTILISATEUR);
			pstmt.setInt(1, article.getNoArticle());
			pstmt.setInt(2, acheteur.getId());
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				enchereRemportee = mapEnchere(rs);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ENCHERE_ECHEC);
			throw businessException;
			
		}
		return enchereRemportee;
	}

	@Override
	public void insertEnchere(Enchere enchere) throws BusinessException {
		if(enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, enchere.getAcheteur().getId());
			pstmt.setInt(2, enchere.getArticleVendu().getNoArticle());
			pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, enchere.getMontantEnchere());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				enchere.setNoEnchere(rs.getInt(1));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
			
		}
		
	}
	
	private Enchere mapEnchere(ResultSet rs) throws SQLException, BusinessException {
		
		int noEnchere = rs.getInt("no_enchere");
		int noUtilisateur = rs.getInt("no_utilisateur");
		int noArticle = rs.getInt("no_article");
		LocalDateTime dateEnchere = rs.getTimestamp("date_enchere").toLocalDateTime();
		int montantEnchere = rs.getInt("montant_enchere");
		
		Utilisateur acheteur = utilisateurManager.recupererUtilisateurParId(noUtilisateur);
		ArticleVendu articleVendu = articleManager.recupererArticle(noArticle);
		
		return new Enchere(noEnchere, dateEnchere, montantEnchere, articleVendu, acheteur);
		
		
	}

}
