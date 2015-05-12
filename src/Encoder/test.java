package Encoder;

public class test {
	private Encoder e =  new Encoder();
	private String x = "";
	
	public test(String s){
		System.out.println("Starting String: " + s);
		byte[] temp = e.encrypt(s);
		System.out.println("Encrypted String: ");
		for(byte b : temp){
			x += b;
		}
		System.out.println(x);
		x = e.decrypt(temp);
		System.out.println("Decrypted String: " + x);
		temp = e.encryptOneWay(s);
		x = "";
		System.out.println("One Way Encrypted String: ");
		for(byte b : temp){
			x += b;
		}
		System.out.println(x);
		x = e.decrypt(temp);
		System.out.println("Smash bois");
	}
	
	public static void main(String[] args){
		new test("Skippable.tv");
	}
}
