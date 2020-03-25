package matrices;

public class Problem85 {

	public static void main(String[] args) {
		int m = 1, n = 4, x = 0;
		int[][] input = new int[m][n];
		for(int i = 0; i < m; i++) {
			for(int j = 0;j < n; j++) {
				input[i][j] = ++x;
			}
		}
		printBoundaryTraversal(input);
	}

	public static void printBoundaryTraversal(int[][] input) {
		int m = input.length;
		int n = input[0].length;
		if(m == 1) {
			printLeftToRight(input, m, n, 0, 0);
			return;
		} else if(n == 1) {
			printTopToBottom(input, m, n, 1, n-1);
			return;
		}
		printLeftToRight(input, m, n, 0, 0);
		printTopToBottom(input, m, n, 1, n - 1);
		printRightToLeft(input, m, n, m-1, n-2);
		printBottomToTop(input, m, n, m-1, 0);
	}

	public static void printLeftToRight(int[][] input, int m, int n, int i, int j) {
		while(j < n) {
			System.out.println(input[i][j++]);
		}
	}

	public static void printRightToLeft(int[][] input, int m, int n, int i, int j) {
		while(j >= 0) {
			System.out.println(input[i][j--]);
		}
	}

	public static void printTopToBottom(int[][] input, int m, int n, int i, int j) {
		while(i < m) {
			System.out.println(input[i++][j]);
		}
	}

	public static void printBottomToTop(int[][] input, int m, int n, int i, int j) {
		while(i >= 1) {
			System.out.println(input[i--][j]);
		}
	}

}
