package amazon;

public class LongestCommonSubstring {

	public static void main(String[] args) {
		int N = 3, M = 2;
		String X = "ABC", Y = "AC";
		solve(N, M, X, Y);
	}

	public static void solve(int N, int M, String X, String Y) {
		int maxLength = 0;
		int maxIndexI = -1, maxIndexJ = -1;
		int[][] dpArray = new int[N][M];
		
		for(int i = 0;i < N; i++) {
			for(int j = 0;j < M; j++) {
				if(X.charAt(i) == Y.charAt(j)) {
					if(i == 0 || j == 0) {
						dpArray[i][j] = 1;
					} else {
						dpArray[i][j] = dpArray[i - 1][j - 1] + 1;
					}
				}
				if(maxLength < dpArray[i][j]) {
					maxLength = dpArray[i][j];
					maxIndexI = i;
					maxIndexJ = j;
				}
			}	
		}
		
		System.out.println(maxLength);
	}
}
