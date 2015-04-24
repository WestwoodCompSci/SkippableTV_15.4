package Encoder;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;

public class Encoder {
	
	//TODO
	private byte[] iv = new byte[3];
	private byte[] key = new byte[3];
	private static Crypt crypt;
	
	
	public Encoder(){
		key[0] = 71;
		key[1] = 22;
		key[2] = 62;
		iv[0] = 63;
		iv[0] = 22;
		iv[0] = 55;
		crypt = new Crypt(key, iv);
	}

	//returns an encoded string of plaintext
	public static String encode(String s){
		String o= "";
		for(int i=0;i<s.length()-1;i+=2)
		{
			String a= s.substring(i,i+1);
			String b= s.substring(i+1,i+2);
			o+=b;
			o+=a;
		}
		if(s.length()%2==1)
			o+=s.substring(s.length()-1,s.length());
		return o;
	}
	
	//returns an encrypted byte array
	public static byte[] encrypt(String s) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, ShortBufferException, BadPaddingException, IOException{
		return crypt.encrypt(s);
	}
	
	//returns a decrypted object (should be a string)
	public static Object decrypt(byte[] b) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, ShortBufferException, BadPaddingException, IOException, ClassNotFoundException{
		return crypt.decrypt(b);
	}
	
	public static String decode(String s){
		return encode(s);
	}
	
	public static String toHash(String s){
		char[] x = s.toCharArray();
		int sum = 0;
		for(int i = 0 ; i < x.length ; i++){
			{
				sum+= (int)x[i]*(9029+i);  
			}
		}
		return Integer.toString(sum%23987);
	}
	
	public static String encodeInt(int i){
		return encode(Integer.toString(i));
	}
}
