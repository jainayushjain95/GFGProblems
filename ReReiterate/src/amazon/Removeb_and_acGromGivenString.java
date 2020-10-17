package amazon;

import java.util.Scanner;

public class Removeb_and_acGromGivenString {

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

	public static String solve(String S) {
		String solution = "";
		int N = S.length();
		
		for(int i = 0;i < N; i++) {
			if(S.charAt(i) == 'b') {
				continue;
			} else if(i < N - 1 && S.charAt(i) == 'a' && S.charAt(i + 1) == 'c') {
				i++;
				continue;
			} else {
				solution = solution + S.charAt(i);
			}
		}
		
		return solution;
	}
}
