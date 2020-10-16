package amazon;

import java.util.Scanner;

public class CountSubsequencesOfType {

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
		int N = S.length();
		int ac = 0, bc = 0, cc = 0;
		for(int i = 0;i < N; i++) {
			if(S.charAt(i) == 'a') {
				ac = 1 + 2 * ac;
			} else if(S.charAt(i) == 'b') {
				bc = ac + 2 * bc;
			} else if(S.charAt(i) == 'c') {
				cc = bc + 2 * cc;
			}
		}
		return cc;
	}
}
