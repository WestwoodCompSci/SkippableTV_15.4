package Testing;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;

import Encoder.Encoder;


public class EncoderTest {
	

	public static void main(String[] args) {
		Encoder e = new Encoder();
		
		String toEncrypt = "Sai is testing";
		
		byte [] encryptedString = e.encrypt(toEncrypt);
		String decrypted = e.decrypt(encryptedString);
		
		System.out.println("String to be encrypted is: " + toEncrypt);
		System.out.println("Encrypted thing: " + encryptedString);
		System.out.println("Decrypted string: " + decrypted);
	}
	
}
