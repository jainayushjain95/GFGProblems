package amazon;

import java.util.Arrays;

public class LongestDistinctCharsInString {

	public static void main(String[] args) {
		String S = "geeksforgeeks";
		solve(S);
	}

	public static void solve(String S) {
		int maxLength = 0, n = S.length();
		int[] alpha = new int[255];
		Arrays.fill(alpha, -1);
		
		int beginIndex = 0, endIndex = 0;
		
		for(; endIndex < n; endIndex++) {
			beginIndex = Math.max(beginIndex, 1 + alpha[S.charAt(endIndex)]);
			int length = endIndex - beginIndex + 1;
			if(length > maxLength) {
				maxLength = length;
			}
			alpha[S.charAt(endIndex)] = endIndex;
		}
		
		System.out.println(maxLength);
	}
}
