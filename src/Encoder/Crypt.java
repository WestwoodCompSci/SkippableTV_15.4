package Encoder;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Crypt {
	
	private Cipher myCipher;
	private SecretKey myKey;
	
	public Crypt(){
		try{
			KeyGenerator gen = KeyGenerator.getInstance("AES");
			gen.init(128);
			myKey = gen.generateKey();
			myCipher = Cipher.getInstance("AES");
		}
		catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] encrypt(String plaintext){
		try {
			myCipher.init(Cipher.ENCRYPT_MODE, myKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		byte[] input = plaintext.getBytes();
		byte[] encrypted = new byte[myCipher.getOutputSize(input.length)];
		
		try {
			encrypted = myCipher.doFinal(input);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		
		return encrypted;
	}
	
	public String decrypt(byte[] DATA){
		try {
			myCipher.init(Cipher.DECRYPT_MODE, myKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		byte[] decrypted = new byte[myCipher.getOutputSize(DATA.length)];
		
		try {
			decrypted = myCipher.doFinal(DATA);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		
		return this.toString(decrypted);
	}
	
	private String toString(byte[] b){
		String s = "";
		
		for(byte x : b){
			s += (char) x;
		}
		
		return s;
	}
}