package amazon;

public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		int n = 20000;
		String S = "";
		for(int i = 0;i < n; i++) {
			S += (char)(97 + Math.random() * (26)); 
		}
		solveDP(S);
		solveBF(S);
	}
	
	public static String solveBF(String S) {
		String solution = "";
		int maxLength = 0;
		
		long totalCount = 0;
		
		for(int i = 0;i < S.length(); i++) {
			for(int j = i; j < S.length(); j++) {
				boolean isPalindrome = isPalindrome(S, i, j);
				totalCount = totalCount + j - i + 1;
				if(isPalindrome) {
					int length = j - i + 1;
					if(length > maxLength) {
						maxLength = length;
						solution = S.substring(i, j + 1);
					}
				}
			}
		}
		System.out.println(totalCount);
		return solution;
	}
	
	public static String solveDP(String S) {
		long totalCount = 0;
		String solution = "";
		int maxLength = 0, n = S.length();
		boolean[][] dpArray = new boolean[n][n];
		
		for(int i = 0;i < n; i++) {
			totalCount++;
			dpArray[i][i] = true;
			int length = (dpArray[i][i]) ? 1 : -1;
			if(length > maxLength) {
				maxLength = length;
				solution = S.substring(i, i + 1);
			}
		}
		for(int i = 0;i < n - 1; i++) {
			totalCount++;
			dpArray[i][i + 1] = S.charAt(i) == S.charAt(i + 1);
			int length = (dpArray[i][i + 1]) ? 2 : -1;
			if(length > maxLength) {
				maxLength = length;
				solution = S.substring(i, i + 2);
			}
 		}

		int columnCheck = 1;
		
		for(int i = n - 3; i >= 0; i--) {
			int k = 0, j = n - 1;
			while(k < columnCheck) {
				totalCount++;
				dpArray[i][j] = ((dpArray[i + 1][j - 1])) && (S.charAt(i) == S.charAt(j));
				int length = (dpArray[i][j]) ? j - i + 1 : -1;
				if(length >= maxLength) {
					maxLength = length;
					solution = S.substring(i, j + 1);
				}
				k++;
				j--;
			}
			columnCheck++;
		}
		System.out.println(totalCount);
		return solution;
	}
	
	public static void print(boolean[][] dpArray) {
		System.out.println();
		int n = dpArray.length;
		for(int i = 0;i < n; i++) {
			for(int j = 0;j < n; j++) {
				System.out.print(((dpArray[i][j]) ? 1 : 0) + " ");
			}
			System.out.println();
		}
		System.out.println("\n");
	}
	
	public static boolean isPalindrome(String s, int beginIndex, int endIndex) {
		if(endIndex == beginIndex) {
			return true;
		}
		if(beginIndex + 1 == endIndex) {
			return s.charAt(beginIndex) == s.charAt(endIndex);
		} 
		if(endIndex < beginIndex) {
			return false;
		}
		if(s.charAt(beginIndex) != s.charAt(endIndex)) {
			return false;
		}
		return isPalindrome(s, beginIndex + 1, endIndex - 1);
	}

}
