package amazon;

public class LongestPalindromicSubsequence {

	public static void main(String[] args) {
		String S = "hellohellohello";
		solve(S);
	}

	public static void solve(String S) {
		int N = S.length(), maxLength = 1;
		String solution = "";
		int[][] dpArray = new int[N][N];

		for(int i = 0;i < N; i++) {
			dpArray[i][i] = 1;
		}

		int rowIndex = N - 2, columnCount = 0;

		while(rowIndex >= 0) {
			columnCount++;
			int columnIndex = N - columnCount;
			for(int i = 0;i < columnCount; i++) {
				if(S.charAt(rowIndex) == S.charAt(columnIndex)) {
					dpArray[rowIndex][columnIndex] = 2 + dpArray[rowIndex + 1][columnIndex - 1];
				} else {
					if(rowIndex == 0 && columnIndex == 0) {
						dpArray[rowIndex][columnIndex] = 1;
					} else if(columnIndex == 0) {
						dpArray[rowIndex][columnIndex] = dpArray[rowIndex + 1][columnIndex]; 
					} else {
						dpArray[rowIndex][columnIndex] = Math.max(dpArray[rowIndex + 1][columnIndex], dpArray[rowIndex][columnIndex - 1]);
					}
				}
				if(dpArray[rowIndex][columnIndex] > maxLength) {
					maxLength = dpArray[rowIndex][columnIndex];
				}
				columnIndex++;
			}
			rowIndex--;
		}
//		System.out.println();
//		for(int i = 0;i < N; i++) {
//			for(int j = 0;j < N; j++) {
//				System.out.print(dpArray[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		
		System.out.println(maxLength);
	}
}
