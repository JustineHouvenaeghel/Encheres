package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;

public class RetraitsDAOJdbcImpl implements RetraitsDAO {

	//RETRAITS
	private static final String SELECT_ALL_RETRAITS = "SELECT * FROM RETRAITS;";
	
	private static final String SELECT_RETRAIT = "SELECT * FROM RETRAITS WHERE no_article = ?;";
	
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS "
													+ "(no_article, rue, code_postal, ville)"
													+ " VALUES (?, ?, ?, ?);";

	private static final String UPDATE_RETRAIT = "UPDATE RETRAITS SET "
													+ "rue = ?, code_postal = ?, ville = ? "
													+ "WHERE no_article = ?;";
	
	private static final String DELETE_RETRAIT = "DELETE FROM RETRAITS WHERE no_article = ?;";

	@Override
	public List<Retrait> selectAllRetraits() throws BusinessException {
		List<Retrait> liste = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_RETRAITS);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				liste.add(mapRetrait(rs));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_RETRAIT_ECHEC);
			throw businessException;
			
		}
		return liste;
	}

	@Override
	public Retrait selectRetrait(int noArticle) throws BusinessException {
		Retrait retrait = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_RETRAIT);
			pstmt.setInt(1, noArticle);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				retrait = mapRetrait(rs);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_RETRAIT_INEXISTANT);
			throw businessException;
			
		}
		return retrait;
	}

	@Override
	public void insertRetrait(Retrait retrait) throws BusinessException {
		if(retrait == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_RETRAIT);
			pstmt.setInt(1, retrait.getArticle().getNoArticle());
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
			
		}
		
	}

	@Override
	public void updateRetrait(ArticleVendu article, Retrait retrait) throws BusinessException {
		if(retrait == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_RETRAIT_NULL);
			throw businessException;
			
		}
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_RETRAIT);
			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2, retrait.getCodePostal());
			pstmt.setString(3, retrait.getVille());
			pstmt.setInt(4, article.getNoArticle());
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_RETRAIT_ECHEC);
			throw businessException;
		}
		
	}

	@Override
	public void deleteRetrait(ArticleVendu article) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_RETRAIT);
			pstmt.setInt(1, article.getNoArticle());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_RETRAIT_ERREUR);
			throw businessException;
		}
		
	}
	
	
	private Retrait mapRetrait(ResultSet rs) throws SQLException, BusinessException {
		
		String rue = rs.getString("rue");
		String codePostal = rs.getString("code_postal");
		String ville = rs.getString("ville");

		return new Retrait(rue, codePostal, ville);
	}

}
