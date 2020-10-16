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
		System.out.println(sb.toString());
	}

	public static int solve(String S) {
		int solution = 4, betAmount = 1, N = S.length();
		for(int i = 0;i < N; i++) {
			if(betAmount > solution) {
				solution = -1;
				break;
			} else	if(S.charAt(i) == 'W') {
				solution = solution + betAmount;
				betAmount = 1;
			} else {
				if(solution < betAmount) {
					solution = -1;
					break;
				}
				solution = solution - betAmount;
				betAmount = 2 * betAmount;
			}
		}
		return solution;
	}
}
