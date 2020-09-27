package strings;

public class NaivePatternSearch {

	public static void main(String[] args) {
		String text = "ABCABCDABCD";
		String pattern = "ABCD";
		patternSearchA(text, pattern);
	}

	/*
	 * O[(N - M + 1) * M]
	 */
	public static void patternSearch(String text, String pattern) {
		int m = pattern.length();
		int n = text.length();
		if(m > n) {
			System.out.println("Not Present");
			return;
		}
		
		int noOfWindows = n - m + 1;
		for(int i = 0;i < noOfWindows; i++) {
			int j = 0;
			while(j < m && text.charAt(i + j) == pattern.charAt(j)) {
				j++;
			}
			if(j == pattern.length()) {
				System.out.println(i);
			}
		}
	}
	
	
	/*
	 * When all characters in pattern are distinct
	 */
	public static void patternSearchA(String text, String pattern) {
		int m = pattern.length();
		int n = text.length();
		if(m > n) {
			System.out.println("Not Present");
			return;
		}
		int noOfWindows = n - m + 1;
		for(int i = 0;i < noOfWindows;) {
			int j = 0;
			while(j < m && text.charAt(i + j) == pattern.charAt(j)) {
				j++;
			}
			if(j == pattern.length()) {
				System.out.println(i);
			}
			if(j == 0) {
				i++;
			} else {
				i = i + j;	
			}
		}
	}
}
