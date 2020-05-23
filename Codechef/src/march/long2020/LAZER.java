package march.long2020;

import java.util.Scanner;

public class LAZER {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			str.append(solve(sc.nextInt(), sc.nextInt()) + "\n");
			t--;
		}
	}

	public static String solve(int r0, int c0) {
		StringBuilder str = new StringBuilder();
		return str.toString();
	}
	
}
