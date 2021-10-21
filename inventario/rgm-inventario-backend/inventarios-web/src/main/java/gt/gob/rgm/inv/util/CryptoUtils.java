package gt.gob.rgm.inv.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;

public class CryptoUtils {
	private static final int ITERATIONS = 10000;
	private static final int LENGTH = 512;
	
	public static String hash(String original, String salt) {
		char[] originalChars = original.toCharArray();
		byte[] saltBytes = salt.getBytes();
		
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			PBEKeySpec spec = new PBEKeySpec(originalChars, saltBytes, ITERATIONS, LENGTH);
			SecretKey key = skf.generateSecret(spec);
			byte[] res = key.getEncoded();
			
			return Hex.encodeHexString(res);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return new StringBuffer(original + salt).reverse().toString();
	}
}
