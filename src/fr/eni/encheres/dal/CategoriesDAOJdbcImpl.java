package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

public class CategoriesDAOJdbcImpl implements CategoriesDAO {
	
	private static UtilisateursManager utilisateurManager = new UtilisateursManager();

	//CATEGORIES
	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGORIES;";
	
	private static final String SELECT_CATEGORIE = "SELECT * FROM CATEGORIES WHERE no_categorie = ?;";
	
	private static final String INSERT_CATEGORIE = "INSERT INTO CATEGORIES "
													+ "(libelle) "
													+ "VALUES (?);";

	private static final String UPDATE_CATEGORIE = "UPDATE CATEGORIES SET "
													+ "libelle = ? "
													+ "WHERE no_categorie = ?;";
	
	private static final String DELETE_CATEGORIE = "DELETE FROM CATEGORIES WHERE no_categorie = ?;";

	//CATEGORIES
	@Override
	public List<Categorie> selectAllCategories() throws BusinessException {
		
		List<Categorie> liste = new ArrayList<>();
			
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_CATEGORIES);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				liste.add(mapCategorie(rs));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_CATEGORIE_ECHEC);
			throw businessException;
			
		}
		return liste;
	}

	@Override
	public Categorie selectCategorie(int noCategorie) throws BusinessException {
		Categorie categorie = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIE);
			pstmt.setInt(1, noCategorie);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				categorie = mapCategorie(rs);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_CATEGORIE_INEXISTANT);
			throw businessException;
			
		}
		return categorie;
	}

	@Override
	public void insertCategorie(Categorie categorie) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_CATEGORIE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, categorie.getLibelle());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				categorie.setNoCategorie(rs.getInt("no_categorie"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public void updateCategorie(Categorie categorie) throws BusinessException {
		if(categorie == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_CATEGORIE_NULL);
			throw businessException;
			
		}
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CATEGORIE);
			pstmt.setInt(1, categorie.getNoCategorie());
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_CATEGORIE_ECHEC);
			throw businessException;
		}
	}
	
	@Override
	public void deleteCategorie(int noCategorie) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_CATEGORIE);
			pstmt.setInt(1, noCategorie);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_CATEGORIE_ERREUR);
			throw businessException;
		}
		
	}

	private Categorie mapCategorie(ResultSet rs) throws SQLException {
		
		int noCategorie = rs.getInt("no_categorie");
		String libelle = rs.getString("libelle");
		
		return new Categorie(noCategorie, libelle);
		
	}

}
