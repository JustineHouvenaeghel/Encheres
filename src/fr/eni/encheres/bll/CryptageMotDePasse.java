package fr.eni.encheres.bll;

import org.mindrot.jbcrypt.BCrypt;

public abstract class CryptageMotDePasse {
	
	private final static int logRounds;
	
	static {
		logRounds = 11;
		
	}
	
	public static String hash(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
		
	}
	
	public static boolean verifyHash(String password, String hash) {
		return BCrypt.checkpw(password, hash);
		
	}
	
}
