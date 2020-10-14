package amazon;

import java.util.Scanner;

public class SmallestDistinctWindow {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = Integer.parseInt(sc.nextLine());
		while(t != 0) {
			t--;
			System.out.println(solve(sc.nextLine()));
		}
	}

	public static int solve(String S) {
		int smallestWindowLength = Integer.MAX_VALUE, N = S.length(), left = 0, right = 0;
		int[] alphaStart = new int[255];
		for(int i = 0;i < N; i++) {
			if(alphaStart[S.charAt(i)] == 0) {
				alphaStart[S.charAt(i)]++;	
			}
		}
		
		int[] alpha = new int[255];
		alpha[S.charAt(0)]++;
		
		while(left < N && right < N) {
			if(matches(alphaStart, alpha)) {
				int length = right - left + 1;
				if(length < smallestWindowLength) {
					smallestWindowLength = length;
				}
				left++;
				alpha[S.charAt(left - 1)]--;
			} else {
				right++;
				if(right < N) {
					alpha[S.charAt(right)]++;	
				}
			}
		} 
		return smallestWindowLength;
	}
	
	public static boolean matches(int[] alphaStart, int[] alpha) {
		boolean matches = true;
		for(int i = 0;i < alpha.length; i++) {
			if(alphaStart[i] == 1 && alpha[i] < 1) {
				matches = false;
				break;
			}
		}
		return matches;
	}
}
