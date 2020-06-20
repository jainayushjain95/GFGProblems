package strings.geeksforgeeks;

public class NaivePatternSearch {

	public static void main(String[] args) {
		String text = "geeksforleeks";
		String pattern = "ee";
		solve(text, pattern);
	}

	public static void solve(String text, String pattern) {
		if(pattern.length() <= text.length()) {
			int n = text.length();
			int m = pattern.length();
			int noOfWindows = n - m + 1;
			int i, j;
			for(i = 0;i < noOfWindows; i++) {
				for(j = 0; j < m; j++) {
					if(text.charAt(j + i) != pattern.charAt(j)) {
						break;
					}
				}
				if(j == m) {
					System.out.println(i);
				}
			}
		}
	}
	
}
