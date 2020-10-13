package amazon;

public class InterleavedStrings {

	public static void main(String[] args) {
		String a = "X", b = "YXYXYXYXY", c = "YYYXXXYXYX";
//		String a = "abcdefg", b = "hijklmn", c = "abcdefghijklmn";
		System.out.println(isInterLeaveSolve(a, b, c));
	}
	
	public static boolean isInterLeaveSolve(String a, String b, String c) {
		int m = a.length(), n = b.length(), p = c.length();
		boolean isInterLeave = (m + n == p);
		
		boolean[][] dpArray = new boolean[m + 1][n + 1];
		
		if(isInterLeave) {
			for(int i = 0;i <= m; i++) {
				for(int j = 0;j <= n; j++) {
					if(i == 0 && j == 0) {
						dpArray[i][j] = true;
					} else if(i == 0) {
						dpArray[i][j] = (b.charAt(j - 1) == c.charAt(i + j - 1)) && (dpArray[i][j - 1]);
					} else if(j == 0) {
						dpArray[i][j] = a.charAt(i - 1) == c.charAt(i + j - 1) && (dpArray[i - 1][j]);
					} else {
						char x = a.charAt(i - 1);
						char y = b.charAt(j - 1);
						char z = c.charAt(i + j - 1);
						
						if(x == z && y != z) {
							dpArray[i][j] = dpArray[i - 1][j];
						} else if(x != z && y == z) {
							dpArray[i][j] = dpArray[i][j - 1];
						} else if(x == z && y == z) {
							dpArray[i][j] = dpArray[i - 1][j] || dpArray[i][j - 1];
						}
					}
				}
			}
			isInterLeave = dpArray[m][n];
		}
		
		return isInterLeave;
	}

	public static boolean isInterLeaveSolveRecursive(String a, String b, String c, int i, int j, int k) {
		System.out.println(i + ", " + j + ", " + k);
		if(i >= a.length() && j >= b.length() && k == c.length()) {
			return true;
		}
		
		return (i < a.length() && k < c.length() && a.charAt(i) == c.charAt(k) && isInterLeaveSolveRecursive(a, b, c, i + 1, j, k + 1))
				|| (j < b.length() && k < c.length() && b.charAt(j) == c.charAt(k) && isInterLeaveSolveRecursive(a, b, c, i, j + 1, k + 1));
	}
	
}
