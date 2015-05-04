package Encoder;

public class test {
	private Encoder e =  new Encoder();
	
	public test(String s){
		System.out.println("Starting String: " + s);
		byte[] temp = e.encrypt(s);
		System.out.println("Encrypted String: ");
		for(byte b : temp){
			System.out.println(b);
		}
		String x = e.decrypt(temp);
		System.out.println("Decrypted String: " + x);
		temp = e.encryptOneWay(s);
		System.out.println("One Way Encrypted String: ");
		for(byte b : temp){
			System.out.println(b);
		}
		x = e.decrypt(temp);
		System.out.println("Smash bois");
	}
	
	public static void main(String[] args){
		new test("Dingus");
	}
}
