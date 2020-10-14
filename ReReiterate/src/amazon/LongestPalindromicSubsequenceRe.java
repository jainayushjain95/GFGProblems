package amazon;

import java.util.Scanner;

public class LongestPalindromicSubsequenceRe {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = Integer.parseInt(sc.nextLine());
		while(t != 0) {
			System.out.println(lps(sc.nextLine()));
			t--;
		}
	}
	
	public static int lps(String S) {
		int N = S.length(), maxLength = -1;
		int[][] dpArray = new int[N][N];
		
		for(int i = 0;i < N; i++) {
			dpArray[i][i] = 1;
		}
		
		
		int rowIndex = N - 2;
		while(rowIndex >= 0) {
			int columnIndex = 1 + rowIndex;
			while(columnIndex < N) {
				if(S.charAt(rowIndex) == S.charAt(columnIndex)) {
					dpArray[rowIndex][columnIndex] = 2 + dpArray[rowIndex + 1][columnIndex - 1];
				} else {
					dpArray[rowIndex][columnIndex] = Math.max(dpArray[rowIndex][columnIndex - 1], dpArray[rowIndex + 1][columnIndex]);
				}
				int length = dpArray[rowIndex][columnIndex];
				if(length > maxLength) {
					maxLength = length;
				}
				columnIndex++;
			}
			rowIndex--;
		}
		
		return maxLength;
	}

}
