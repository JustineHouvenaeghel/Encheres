package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException;
	
	public void deleteUtilisateur(int id) throws BusinessException;
	
	public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException;
	
	public void updateCreditUtilisateur(Utilisateur utilisateur, int credit) throws BusinessException;
	
	public List<Utilisateur> selectAllUtilisateur() throws BusinessException;
	
	public Utilisateur selectById(int id) throws BusinessException;

}
