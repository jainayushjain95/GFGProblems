package may.cookoff2020;

import java.util.HashMap;
import java.util.Scanner;

public class Problem2 {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			str.append(solve(sc.next()) + "\n");
			t--;
		}
		System.out.println(str.toString());
	}

	
	
	
	public static String solve(String s) {
		StringBuilder str = new StringBuilder();
		int noOfWays = 0;
		str.append(noOfWays);
		return str.toString();
	}
}
