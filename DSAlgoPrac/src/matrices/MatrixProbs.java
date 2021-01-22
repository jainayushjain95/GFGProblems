package matrices;

public class MatrixProbs {

	public static void main(String[] args) {
		int[][] a = new int[4][4];

		int start = 1;
		for(int i = 0;i < a.length; i++) {
			for(int j = 0;j < a[i].length; j++) {
				a[i][j] = start++;
			}
		}
		searchInRowWiseAndColumnWiseSortedMatrix(a, 123);	
		
	}

	public static void printSnake(int[][] a) {
		boolean printLeftToRight = true;

		for(int i = 0;i < a.length; i++) {
			if(printLeftToRight) {
				for(int j = 0;j < a[i].length; j++) {
					System.out.print(a[i][j] + " ");
				}	
			} else {
				for(int j = a[i].length - 1;j >= 0; j--) {
					System.out.print(a[i][j] + " ");
				}
			}
			printLeftToRight = !printLeftToRight;
			System.out.println("\n");
		}
	}

	public static void printSpiral(int[][] a) {
		int top = 0, left = 0;
		int right = a[0].length - 1, bottom = a[a.length - 1].length - 1;

		while(top <= bottom && left <=  right) {
			for(int i = left; i <= right; i++) {
				System.out.print(a[top][i] + " ");
			}
			top++;
			
			for(int i = top; i <= bottom; i++) {
				System.out.print(a[i][right] + " ");
			}
			right--;
			
			for(int i = right; i >= left; i--) {
				System.out.print(a[bottom][i] + " ");
			}
			bottom--;
			
			for(int i = bottom; i >= top; i--) {
				System.out.print(a[i][left] + " ");
			}
			left++;
		}

	}
	
	public static void searchInRowWiseAndColumnWiseSortedMatrix(int[][] a, int key) {
		int column = a[0].length - 1, row = 0;
		int indexR = -1, indexC = -1;
		
		while(column >= 0 && row < a.length) {
			if(a[row][column] == key) {
				indexC = column;
				indexR = row;
				break;
			} else if(a[row][column] > key) {
				column--;
			} else {
				row++;
			}
		}
		System.out.println(indexR + ", " + indexC);
	}

	public static void print(int[][] a) {
		for(int i = 0;i < a.length; i++) {
			for(int j = 0;j < a[i].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println("\n");
		}
	}

}
