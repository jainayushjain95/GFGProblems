package amazon;

public class LongestCommonSubstringRe {

	public static void main(String[] args) {
		String X = "ABCDE", Y = "MBACD";
		int N = X.length(), M = Y.length();
		solve(N, M, X, Y);
	}

	public static void solve(int N, int M, String X, String Y) {
		int[][] dpArray = new int[N][M];
		int maxLength = 0;
		
		for(int i = 0;i < N; i++) {
			for(int j = 0;j < M; j++) {
				if(X.charAt(i) == Y.charAt(j)) {
					if(i == 0 || j == 0) {
						dpArray[i][j] = 1;
					} else {
						dpArray[i][j] = dpArray[i - 1][j - 1] + 1;
					}
				} else {
					dpArray[i][j] = 0;
				}
				maxLength = Math.max(maxLength, dpArray[i][j]);
			}
		}
		System.out.println(maxLength);
	}
	
}
