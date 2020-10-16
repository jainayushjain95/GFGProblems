package amazon;

import java.util.Scanner;

public class CountPalindromeSubStringsOfString {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(sc.nextLine());
		while(t != 0) {
			t--;
			sb.append(solve(Integer.parseInt(sc.nextLine()), sc.nextLine()) + "\n");
		}
		System.out.println(sb.toString());
	}

	public static int solve(int N, String S) {
		int count = 0;
		boolean[][] dpArray = new boolean[N][N];

		for(int i = 0; i < N; i++) {
			dpArray[i][i] = true;
		}

		for(int i = 0; i < N - 1; i++) {
			dpArray[i][i + 1] = S.charAt(i) == S.charAt(i + 1);
			if(dpArray[i][i + 1]) {
				count++;
			}
		}

		int rowIndex = N - 3, columnIndex = N - 1;

		for(int i = rowIndex; i >= 0; i--) {
			for(int j = columnIndex; j < N; j++) {
				dpArray[i][j] = S.charAt(i) == S.charAt(j) && dpArray[i + 1][j - 1];
				if(dpArray[i][j]) {
					count++;
				}
			}
			columnIndex--;
		}

		return count;
	}
}
