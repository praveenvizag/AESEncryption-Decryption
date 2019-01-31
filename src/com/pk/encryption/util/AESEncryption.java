package com.pk.encryption.util;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryption {
	
	private static final String ALGO = "AES";
	private  byte[] key ;

	public AESEncryption(String key) {
		this.key = key.getBytes();
	}
	
	private String encryptUtil(String valueToEncrypt) throws Exception{
		Key secretkey = generateKey();
		System.out.println("Algorithm " + secretkey.getAlgorithm());
		Cipher cipher = Cipher.getInstance(ALGO);
		cipher.init(Cipher.ENCRYPT_MODE, secretkey);
		byte[] encValue = cipher.doFinal(valueToEncrypt.getBytes());
		String encryptedValue = Base64.getEncoder().encodeToString(encValue);
		return encryptedValue;
		
	}
	
	private String decryptUtil(String valueToDecrypt) throws Exception{
		Key secretkey = generateKey();
		Cipher cipher = Cipher.getInstance(ALGO);
		cipher.init(Cipher.DECRYPT_MODE, secretkey);
		byte[] decode = Base64.getDecoder().decode(valueToDecrypt);
		byte[] decryptedByteArray = cipher.doFinal(decode);
		String decryptedValue = new String(decryptedByteArray);
		return decryptedValue;
		
	}

	private Key generateKey() {
		Key secretKeySpec = new SecretKeySpec(key, ALGO);
		return secretKeySpec;
		
	}
	
	public static void main(String []args) throws Exception {
		
		AESEncryption aesEncryption = new AESEncryption("abcdfertyhng1235");
		String encryptedString = aesEncryption.encryptUtil("DQOPK4410H");
		System.out.println("encryptedString " + encryptedString);
		//AESEncryption aesEncryption1 = new AESEncryption("123wertgfdscvbnh");
		String decryptedString = aesEncryption.decryptUtil(encryptedString);
		System.out.println("decryptedString " + decryptedString);
	}
	

}
