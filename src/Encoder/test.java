package Encoder;

import javax.swing.SwingUtilities;

public class test {
	private Encoder myEncoder = new Encoder();
	
	public test(){
		byte[] man = myEncoder.encrypt("Will's a butt");
		System.out.println(man);
		String men = myEncoder.decrypt(man);
		System.out.println(men);
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new test();
			}
		});
	}
}
