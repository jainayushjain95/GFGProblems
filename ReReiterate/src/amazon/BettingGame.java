package amazon;

import java.util.ArrayList;
import java.util.Scanner;

public class BettingGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(sc.nextLine());
		while(t != 0) {
			t--;
			sb.append(solve(sc.nextLine()) + "\n");
		}
	}

	public static int solve(String S) {
		int solution = 0;
		
		return solution;
	}
}
