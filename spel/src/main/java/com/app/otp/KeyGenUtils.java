package com.app.otp;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;

public class KeyGenUtils {
	
	 private static final String HMAC_ALGORITHM = "HmacSHA1"; // Can be HmacSHA1, HmacSHA256, or HmacSHA512
	 private static final Base64 base64 = new Base64();
	private static final Base32 base32 = new Base32();
	  
	    public static String generateNewKey(){
	        // Create a KeyGenerator for the specified HMAC algorithm
	        KeyGenerator keyGenerator;
			try {
				keyGenerator = KeyGenerator.getInstance(HMAC_ALGORITHM);
			} catch (NoSuchAlgorithmException e) {
				System.err.println("Error Generating new Key");
				return null;
			}
	        
	        // Generate a random secret key using a secure random generator
	        keyGenerator.init(new SecureRandom());
	        SecretKey secretKey = keyGenerator.generateKey();

	        return base32.encodeAsString(secretKey.getEncoded());
	    }

	    public static SecretKey generateSecretFromKey(String persistedKey){
	    	byte[] decode = base32.decode(persistedKey);
	    	SecretKeySpec secretKeySpec = new SecretKeySpec(decode, HMAC_ALGORITHM);
	    	return secretKeySpec;
	    }
//	    public static Key generateKeyFromSecret(String secret) throws NoSuchAlgorithmException {
//	        byte[] decodedKey = Base64.getDecoder().decode(secret);
//
//	        // Create a secret key spec from the decoded key
//	        SecretKey secretKey = new SecretKeySpec(decodedKey, HMAC_ALGORITHM);
//
//	        return secretKey;
//	    }
}
