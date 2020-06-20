package strings.geeksforgeeks;

public class KMP {

	public static void main(String[] args) {
		String pattern = "bba";
		String text = "abba";
		kmpSearch(text, pattern);
	}

	public static void kmpSearch(String text, String pattern) {
		int[] lpsArray = getLPSArray(pattern);
		int i = 0, n = text.length(), j = 0, m = pattern.length();
		while(i < n) {
			if(text.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
			}
			if(j == m) {
				System.out.println(i - j);
				j = lpsArray[j - 1];
			} else if(j < m && text.charAt(i) != pattern.charAt(j)) {
				if(j == 0) {
					i++;
				} else {
					j = lpsArray[j - 1];
				}
			}
		}
	}
	
	public static int[] getLPSArray(String pattern) {
		int n = pattern.length();
		int[] lpsArray = new int[n];
		int j = 0;
		for(int i = 1; i < n; i++) {
			if(pattern.charAt(i) == pattern.charAt(j)) {
				lpsArray[i] = ++j;
			} else {
				j = 0;
				lpsArray[i] = j;
			}
		}
		return lpsArray;
	}
	
}
