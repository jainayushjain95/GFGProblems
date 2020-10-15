package amazon;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RearrangeChars {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = Integer.parseInt(sc.nextLine());
		while(t != 0) {
			System.out.println(solve(sc.nextLine()));
			t--;
		}
	}
	
	public static int solve(String S) {
		int ans = 1, N = S.length(), maxF = 0;
		int[] alpha = new int[26];
		for(int i = 0;i < N; i++) {
			alpha[S.charAt(i) - 'a']++;
			if(maxF < alpha[S.charAt(i) - 'a']) {
				maxF = alpha[S.charAt(i) - 'a'];
			}
		}
		if(maxF > N/2) {
			ans = 0;
		}
		return ans;
	}
	
	
}
