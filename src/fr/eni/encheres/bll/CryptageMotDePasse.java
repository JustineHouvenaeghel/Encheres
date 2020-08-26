package fr.eni.encheres.bll;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public abstract class CryptageMotDePasse {
	
	private static String password = "blabla";
	
	private static byte[] salt = new String("12345678").getBytes();
	
	private static int iterationCount = 40000;
	
	private static int keyLength = 128;
	
	private static SecretKeySpec key;
	
	static {

		try {
			CryptageMotDePasse.key = createSecretKey(password.toCharArray(), salt, iterationCount, keyLength);
			System.out.println(key.getEncoded().toString());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	private static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
		PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
		SecretKey keyTmp = keyFactory.generateSecret(keySpec);
		return new SecretKeySpec(keyTmp.getEncoded(), "AES");
		
	}
	
	private static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
	
	public static String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException {
		Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		pbeCipher.init(Cipher.ENCRYPT_MODE, key);
		AlgorithmParameters parameters = pbeCipher.getParameters();
		IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
		byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
		byte[] iv = ivParameterSpec.getIV();
		return base64Encode(iv) + ":" + base64Encode(cryptoText);
	}
	
//	private static byte[] base64Decode(String property) throws IOException {
//        return Base64.getDecoder().decode(property);
//    }
//	
//	public static String decrypt(String string, SecretKeySpec key) throws GeneralSecurityException, IOException {
//        String iv = string.split(":")[0];
//        String property = string.split(":")[1];
//        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
//        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
//    }
}
