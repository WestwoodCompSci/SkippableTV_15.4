package Encoder;

public class Encoder {

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
	
	/*public static String encodeShow(Show x){
		String s = "";
		s.add(encode(x.getTitle()) + "\n");
		s.add(encodeInt(x.getTime()) + "\n");
		s.add(encodeInt(x.getCuTime()) + "\n");
		s.add(encodeInt(x.getShows()) + "\n");
		s.add(encodeInt(x.getSeasons()) + "\n");
		return s;
	}*/
}
