package strings;

public class KMPAlgorithm {

	public static void main(String[] args) {
		String pattern = "abacabad";
		String text = "";
		kmpSearch(text, pattern);
	}

	public static void kmpSearch(String text, String pattern) {
		int[] lpsArray = getLPSArray(pattern);
		for(int x : lpsArray) {
			System.out.println(x);
		}
	}
	
	public static int[] getLPSArray(String pattern) {
		int m = pattern.length();
		int[] lpsArray = new int[m];
		
		int j = 0;
		for(int i = 1;i < m; i++) {
			if(pattern.charAt(j) == pattern.charAt(i)) {
				lpsArray[i] = ++j;
			} else {
				lpsArray[i] = 0;
				j = 0;
			}
		}
		
		return lpsArray;
	}
}
