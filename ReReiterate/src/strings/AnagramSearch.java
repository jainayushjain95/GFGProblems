package strings;

public class AnagramSearch {

	public static void main(String[] args) {
		String text = "geeksforgeeks";
		String pattern = "gforg";
		System.out.println(anagramSearch(text, pattern));
	}
	
	public static boolean anagramSearch(String text, String pattern) {
		boolean isPresent = true;
		int[] alphaText = new int[256];
		int[] alphaPattern = new int[256];
		for(int i = 0; i < pattern.length(); i++) {
			alphaPattern[pattern.charAt(i)]++;
		}
		for(int i = 0; i < pattern.length(); i++) {
			alphaText[text.charAt(i)]++;
		}
		
		int n = text.length(), m = pattern.length(), noOfWIndows = n - m + 1;
		
		isPresent = areSame(alphaText, alphaPattern);
		
		for(int i = 1;i < noOfWIndows && !isPresent; i++) {
			alphaText[text.charAt(i - 1)]--;
			alphaText[text.charAt(i + m - 1)]++;
			isPresent = areSame(alphaText, alphaPattern);
		}
		
		return isPresent;
	}

	public static boolean areSame(int[] alphaText, int[] alphaPattern) {
		for(int i = 0;i < 256; i++) {
			if(alphaPattern[i] != alphaText[i]) {
				return false;
			}
		}
		return true;
	}
	
}
