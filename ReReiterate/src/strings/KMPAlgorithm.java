package strings;

public class KMPAlgorithm {

	public static void main(String[] args) {
		String text = "ababcababaad";
		String pattern = "ab";
		kmpSearch(text, pattern);
	}

	public static void kmpSearch(String text, String pattern) {
		int[] lpsArray = getLPSArray(pattern);
		int m = pattern.length(), n = text.length(), i = 0, j = 0;
		
		while(i < n) {
			if(pattern.charAt(j) == text.charAt(i)) {
				i++;
				j++;
			}
			if(j == m) {
				System.out.println(i - j);
				j = lpsArray[j - 1];
			}
			if(j < m && i < n && text.charAt(i) != pattern.charAt(j)) {
				if(j == 0) {
					i++;
				} else {
					j = lpsArray[j - 1];
				}
			}
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
