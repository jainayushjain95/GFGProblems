package amazon;

import java.util.Scanner;

public class ColumnNameFromColumnNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = Integer.parseInt(sc.nextLine());
		while(t != 0) {
			t--;
			System.out.println(solve(sc.nextLong()));
		}
	}
	
	public static String solve(long N) {
		String solution = "";
		while(N > 0) {
			solution = (char)('A' + (N - 1) % 26) + solution;
			N = (N - 1)/26;
		}
		
		return solution;
	}

}
