package may.lt2020;

import java.util.Scanner;

public class Problem1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			str.append(solve(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()) + "\n");
			t--;
		}
		System.out.println(str.toString());
	}

	public static String solve(int a1, int a2, int a3, int a4, int a5, int p) {
		StringBuilder str = new StringBuilder();
		int total = p * (a1 + a2 + a3 + a4 + a5);
		int max = 24 * 5;
		if(total <= max) {
			str.append("No");
		} else {
			str.append("Yes");
		}
		return str.toString();
	}

}
