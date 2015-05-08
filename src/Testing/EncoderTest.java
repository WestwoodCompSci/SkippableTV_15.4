package Testing;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;


public class EncoderTest {
	private static EncoderTest e = new EncoderTest();

	public static void main(String[] args) {
		try {
			byte[] x = e.enrypt("dingus");
			for(byte i : x)
			{
				System.out.println(i);
			}
		} catch (InvalidKeyException | InvalidAlgorithmParameterException
				| IllegalBlockSizeException | ShortBufferException
				| BadPaddingException | IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private byte[] enrypt(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
