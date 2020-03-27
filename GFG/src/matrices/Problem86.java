package matrices;

public class Problem86 {

	public static void main(String[] args) {
		int m = 4, n = 5, x = 0;
		int[][] input = new int[m][n];
		for(int i = 0; i < m; i++) {
			for(int j = 0;j < n; j++) {
				input[i][j] = ++x;
			}
		}
		printSpiralTraversal(input);
	}

	public static void printSpiralTraversal(int[][] input) {
		int m = input.length;
		int n = input[0].length;
//		if(m == 1) {
//			printLeftToRight(input, m, n, 0, 0);
//			return;
//		} else if(n == 1) {
//			printTopToBottom(input, m, n, 1, n-1);
//			return;
//		}
		int noOfElementsToBePrinted = m * n;
		int i = 0, j = 0;
		int left = 0, right = n - 1, top = 0, bottom = m - 1;
		while(noOfElementsToBePrinted > 0) {
			printLeftToRight(input, left, right, top);
			top++;
			noOfElementsToBePrinted -= right - left + 1;
			printTopToBottom(input, top, bottom, right);
			right--;
			noOfElementsToBePrinted -= bottom - top + 1;
			printRightToLeft(input, right, left, bottom);
			bottom--;
			noOfElementsToBePrinted -= right - left + 1;
			printBottomToTop(input, bottom, top, left);
			noOfElementsToBePrinted -= bottom - top + 1;
			left++;
		}
	}
	
	public static void printLeftToRight(int[][] input, int left, int right, int top) {
		while(left <= right) {
			System.out.println(input[top][left++]);
		}
	}
	
	public static void printRightToLeft(int[][] input, int right, int left, int bottom) {
		while(right >= left) {
			System.out.println(input[bottom][right--]);
		}
	}

	public static void printTopToBottom(int[][] input, int top, int bottom, int right) {
		while(top <= bottom) {
			System.out.println(input[top++][right]);
		}
	}

	public static void printBottomToTop(int[][] input, int bottom, int top, int left) {
		while(bottom >= top) {
			System.out.println(input[bottom--][left]);
		}
	}

}
