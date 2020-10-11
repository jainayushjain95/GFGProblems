package amazon;

public class LongestCommonSubsequence {
	
	public static void main(String[] args) {
		int N = 6, M = 6;
		String X = "ABCDGH", Y = "AEDFHR";
		solve(N, M, X, Y);
	}

	public static void solve(int N, int M, String X, String Y) {
		int[][] dpArray = new int[N][M];
		
		for(int i = 0;i < N; i++) {
			for(int j = 0;j < M; j++) {
				if(X.charAt(i) == Y.charAt(j)) {
					if(i == 0 || j == 0) {
						dpArray[i][j] = 1;
					} else {
						dpArray[i][j] = dpArray[i - 1][j - 1] + 1;
					}
				} else {
					if(i == 0 && j == 0) {
						dpArray[i][j] = 0;
					} else if(i == 0 && j != 0) {
						dpArray[i][j] = dpArray[i][j - 1];
					} else if(i != 0 && j == 0) {
						dpArray[i][j] = dpArray[i - 1][j];
					} else {
						dpArray[i][j] = Math.max(dpArray[i][j - 1], dpArray[i - 1][j]);
					}
				}
			}
		}
		
		System.out.println(dpArray[N - 1][M - 1]);
	}

}
