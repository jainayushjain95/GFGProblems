package strings.gfg;

public class StringProbs {

	public static void main(String[] args) {
		String text = "aaaaa";
		String pattern = "aaabaaaac";
		int[] lpsArray = getLPSArray(pattern);
		for(int i = 0;i < lpsArray.length; i++) {
			System.out.print(lpsArray[i] + " ");
		}
	}
	
	public static void isSubsequence(String a, String b) {
		boolean isSubsequence = false;
		int i = 0, j = 0;
		while(i < a.length() && j < b.length()) {
			if(a.charAt(i) == b.charAt(j)) {
				i++;
				j++;
			} else {
				i++;
			}
		}
		isSubsequence = j == b.length();
		System.out.println(isSubsequence);
	}
	
	public static void getLeftMostRepeatingCharacter(String a) {
		int[] alpha = new int[256];
		int minIndex = Integer.MAX_VALUE;
		for(int i = 0; i < alpha.length; i++) {
			alpha[i] = -1;
		}
		for(int i = 0;i < a.length(); i++) {
			if(alpha[a.charAt(i) - 65] == -1) {
				alpha[a.charAt(i) - 65] = i;
			} else {
				minIndex = Math.min(minIndex, alpha[a.charAt(i) - 65]);
			}
		}
		System.out.println(minIndex);
	}
	
	public static void getLeftMostNonRepeatingCharacter(String a) {
		int[] alpha = new int[256];
		int minIndex = -1;
		for(int i = 0; i < alpha.length; i++) {
			alpha[i] = -1;
		}
		for(int i = 0;i < a.length(); i++) {
			if(alpha[a.charAt(i) - 65] == -1) {
				alpha[a.charAt(i) - 65] = i;
			} else {
				alpha[a.charAt(i) - 65] = -2;
			}
		}
		for(int i = 0; i < alpha.length && minIndex == -1; i++) {
			if(alpha[i] > -1) {
				minIndex = alpha[i];
			}
		}
		System.out.println(minIndex);
	}
	
	/*
	 * Pattern Searching
	 */
	public static void naivePatternSearch(String text, String pattern) {
		int n = text.length(), m = pattern.length();
		for(int i = 0;i < n - m + 1;i++) {
			int j = 0;
			while(j < m && pattern.charAt(j) == text.charAt(i + j)) {
				j++;
			}
			if(j == m) {
				System.out.println(i);
			}
		}
	}
	
	public static void naivePatternSearchWithDistinctCharsInPattern(String text, String pattern) {
		int n = text.length(), m = pattern.length();
		for(int i = 0;i < n - m + 1;) {
			int j = 0;
			while(j < m && pattern.charAt(j) == text.charAt(i + j)) {
				j++;
			}
			if(j == m) {
				System.out.println(i);
			}
			if(j == 0) {
				i++;
			} else {
				i = i + j;
			}
		}
	}
	
	public static int[] getLPSArray(String pattern) {
		int m = pattern.length(), j = 0;
		int[] lpsArray = new int[m];
		
		for(int i = 1;i < m;) {
			if(pattern.charAt(i) == pattern.charAt(j)) {
				lpsArray[i] = ++j;
				i++; 
			} else {
				if(j == 0) {
					lpsArray[i] = 0;
					i++;
				} else {
					j = lpsArray[j - 1];
				}
			}
		}
		
		return lpsArray;
	}
}
 