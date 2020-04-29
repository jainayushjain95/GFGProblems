package dynamic.programming.problems;

public class DP2 {
	
	public static void main(String[] args) {
		String input1 = "AXYZ";
		String input2 = "BAZ";
		int m = input1.length();
		int n = input2.length();
		
		int[][] dpArray = new int[m + 1][n + 1];
		for(int i = 0; i <= m; i++) {
			for(int j = 0; j <= n; j++) {
				dpArray[i][j] = -1;
			}
		}
		System.out.println(lcsDPTabulaton(input1, input2, m, n));
	}
	
	public static int lcsRecursive(String input1, String input2, int m, int n) {
		if(m == 0 || n == 0) {
			return 0;
		}
		if(input1.charAt(m - 1) == input2.charAt(n - 1)) {
			return 1 + lcsRecursive(input1, input2, m - 1, n - 1);
		} else {
			return Math.max(lcsRecursive(input1, input2, m - 1, n), lcsRecursive(input1, input2, m, n - 1));
		}
	}
	
	public static int lcsDPTabulaton(String input1, String input2, int m, int n) {
		int[][] dpArray = new int[m + 1][n + 1];
		for(int i = 1;i <= m; i++) {
			for(int j = 1;j <= n; j++) {
				if(input1.charAt(i - 1) == input2.charAt(j - 1)) {
					dpArray[i][j] = 1 + dpArray[i - 1][j - 1];
				} else {
					dpArray[i][j] = Math.max(dpArray[i - 1][j], dpArray[i][j - 1]);
				}
			}
		}
		return dpArray[m][n];
	}
	
	public static int lcsDPMemoization(String input1, String input2, int m, int n, int[][] dpArray) {
		if(dpArray[m][n] == -1) {
			if(m == 0 || n == 0) {
				dpArray[m][n] = 0;
			} else {
				if(input1.charAt(m - 1) == input2.charAt(n - 1)) {
					dpArray[m][n] = 1 + lcsDPMemoization(input1, input2, m - 1, n - 1, dpArray);
				} else {
					dpArray[m][n] = Math.max(lcsDPMemoization(input1, input2, m - 1, n, dpArray), lcsDPMemoization(input1, input2, m, n - 1, dpArray));
				}
			}
		}
		return dpArray[m][n];
	}
}
