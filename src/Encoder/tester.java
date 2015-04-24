package Encoder;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;

public class tester {
	private static Encoder e = new Encoder();
	public static void main(String[] args){
		try {
			byte[] x = e.encrypt("dingus");
			for(byte i : x){
				System.out.println(i);
			}
		} catch (InvalidKeyException | InvalidAlgorithmParameterException
				| IllegalBlockSizeException | ShortBufferException
				| BadPaddingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
