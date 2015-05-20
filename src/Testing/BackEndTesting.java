package Testing;
import java.util.ArrayList;

import backend.*;

public class BackEndTesting {

	public static void main (String [] args){
	Rating r = new Rating(1, 1, 1, "ok", 5);
	Rating r1 = new Rating(1, 1, 1, "ok", 3);
	Rating r2 = new Rating(1, 1, 1, "ok", 4);
	Rating r3 = new Rating(1, 1, 1, "ok", 5);
	Rating r4 = new Rating(1, 1, 1, "ok", 1);
	Rating r5 = new Rating(1, 1, 1, "ok", 2);
	Rating r6 = new Rating(1, 1, 1, "ok", 5);
	Rating r7 = new Rating(1, 1, 1, "ok", 3);
	Rating r8 = new Rating(1, 1, 1, "ok", 3);
	Rating r9 = new Rating(1, 1, 1, "ok", 4);
	
	
	ArrayList<Rating> rates = new ArrayList<Rating>();
	
	rates.add(r);
	rates.add(r1);
	rates.add(r2);
	rates.add(r3);
	rates.add(r4);
	rates.add(r5);
	rates.add(r6);
	rates.add(r7);
	rates.add(r8);
	rates.add(r9);
	
	Episode e = new Episode("Rohan sux", "20:32", 1, rates, 1, 1);
	
	System.out.println("Episode average rating by method: " + e.getAverageRating());
	System.out.println("Episode average rating by math: " + (double)(5+3+4+5+1+2+5+3+3+4)/10);
}
	
	
}
