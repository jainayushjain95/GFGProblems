package amazon;

import java.util.Scanner;

public class FormPalindrome {

	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int t = Integer.parseInt(sc.nextLine());
//		while(t != 0) {
//			solve(sc.nextLine());
//			t--;
//		}
		solve("hellohellohello");
	}
	
	public static void solve(String S) {
		System.out.println(S.length() - getLongestPalindromeSubsequenceLength(S));
	}
	
	public static int getLongestPalindromeSubsequenceLength(String S) {
		int maxLength = 1, N = S.length();
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
					dpArray[rowIndex][columnIndex] = Math.max(dpArray[rowIndex + 1][columnIndex], dpArray[rowIndex][columnIndex - 1]);
				}
				
				maxLength = Math.max(maxLength, dpArray[rowIndex][columnIndex]);
				columnIndex++;
			}
			
			rowIndex--;
		}
		
		return maxLength;
	}

}
