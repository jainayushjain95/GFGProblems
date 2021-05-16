package lc.bs;

public class SearchInSorted2DArray {

	public static void main(String[] args) {
		int[][] matrix = {
				{-1, 3}
		};
		System.out.println(searchMatrixSolve(matrix, 3, 0, matrix[0].length - 1));
	}

	public static boolean searchMatrixSolve(int[][] matrix, int target, int rowIndex, int columnIndex) {
		if(rowIndex >= matrix.length || columnIndex < 0) {
			return false;
		}
		if(matrix[rowIndex][columnIndex] == target) {
			return true;
		} else if(matrix[rowIndex][columnIndex] < target) {
			return searchMatrixSolve(matrix, target, rowIndex + 1, columnIndex);
		} else {
			return searchMatrixSolve(matrix, target, rowIndex, columnIndex - 1);
		}
	}


}
