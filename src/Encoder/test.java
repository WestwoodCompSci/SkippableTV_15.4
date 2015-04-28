package Encoder;

public class test {
	private static Encoder myEncoder = new Encoder();
	
	public static void main(String[] args){
		byte[] man = myEncoder.encrypt("Will's a butt");
		System.out.println(man);
		String men = myEncoder.decrypt(man);
		System.out.println(men);
	}
}
