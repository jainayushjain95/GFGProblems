package strings;

public class Problem102 {

	public static void main(String[] args) {
		//Printing all indexes of occurrences
		//n -> input length, m -> pattern length
		String text = "ABABABBABAB";
		String pattern = "AABAAAC";
		int n = text.length(), m = pattern.length();
		patternSearchThree(text, pattern, n, m);
	}
	
	/*
	 * Naive
	 * Duplicates in pattern possible
	 * O(noOfWindows * m)
	 */
	public static void patternSearchOne(String text, String pattern, int n, int m) {
		int noOfWindows = n - m + 1, i, j;
		for(i = 0;i <= noOfWindows; i++) {
			for(j = 0;j < m; j++) {
				if(text.charAt(i + j) != pattern.charAt(j)) {
					break;
				}
			}
			if(m == j) {
				System.out.println(i);
			}
		}
	}
	
	
	/*
	 * Naive
	 * Duplicates in pattern not possible
	 * O(n)
	 */
	public static void patternSearchTwo(String text, String pattern, int n, int m) {
		int noOfWindows = n - m + 1, i, j;
		for(i = 0;i <= noOfWindows;) {
			for(j = 0;j < m; j++) {
				if(text.charAt(i + j) != pattern.charAt(j)) {
					break;
				}
			}
			if(m == j) {
				System.out.println(i);
			}
			if(j == 0) {
				i++;
			} else {
				i = i + j;
			}
		}
	}
	
	
	
	/*
	 * KMP Algorithm
	 * Preprocesses the pattern
	 * Query-> O(n) 
	 */
	
	public static void patternSearchThree(String text, String pattern, int n, int m) {
		int[] lpsArray = getLongestProperPrefixSuffixArray(pattern, m);
		
	}
	
	/*
	 * Preprocessing Step
	 */
	public static int[] getLongestProperPrefixSuffixArray(String pattern, int m) {
		int[] lpsArray = new int[m];
		int len = 0, i  = 1;
		while(i < m) {
			if(pattern.charAt(i) == pattern.charAt(len)) {
				len++;
				lpsArray[i] = len;
				i++;
			} else {
				if(len == 0) {
					lpsArray[i] = 0;
					i++;
				} else {
					len = lpsArray[len - 1];
				}
			}
		}
		return lpsArray;
	}
	
	
	
}
