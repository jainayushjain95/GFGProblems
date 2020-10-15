package amazon;

import java.util.Scanner;

public class NumberFollowingPattern {

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
	
	public static long solve(String S) {
		long ans = 0;
		int N = S.length();
		int[] digits = new int[N + 1];
		int left = 0, right = 0;
		int min = 1;
		
		while(left <= N && right <= N) {
			if(N == right || S.charAt(right) == 'I') {
				fill(digits, left, right, min);
				min = min + right - left + 1;
				left = right + 1;
				right = left;
			} else {
				right++;
			}
		}
		
		for(int i = 0; i <= N; i++) {
			ans = (long) (ans + digits[i] * Math.pow(10, N - i));
		}
		
		return ans;
	}
	
	public static void fill(int[] digits, int left, int right, int min) {
		for(int i = right; i>= left; i--) {
			digits[i] = min++;
		}
	}

}
