package matrices;

public class Problem84 {

	public static void main(String[] args) {
		int m = 4, n = 4, x = 0;
		int[][] input = new int[m][n];
		for(int i = 0; i < m; i++) {
			for(int j = 0;j < n; j++) {
				input[i][j] = ++x;
			}
		}

		printMatrixInSnakePattern(input);
	}

	public static void printMatrixInSnakePattern(int[][] input) {
		int m = input.length;
		for(int i = 0; i < input.length; i++) {
			if(i % 2 == 0) {
				for(int j = 0; j < input[i].length; j++) {
					System.out.println(input[i][j]);
				}
			} else {
				for(int j = input[i].length - 1; j >= 0; j--) {
					System.out.println(input[i][j]);
				}
			}

		}
	}

}
