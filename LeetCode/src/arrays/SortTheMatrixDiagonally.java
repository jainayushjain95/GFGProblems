package arrays;

public class SortTheMatrixDiagonally {

	public static void main(String[] args) {
		int[][] matrix = {
				{1, 2, 3, 4}
		};
		diagonalSortMatrix(matrix);
	}

	public static int[][] diagonalSortMatrix(int[][] matrix) {
		int noOfRows = matrix.length;
		int noOfColumns = matrix[0].length;
		int noOfDiagonals = noOfColumns + noOfRows - 1;
		int rowIndex = noOfRows - 1;
		int columnIndex = 0;
		int diagonalSize = noOfRows - rowIndex;
		while(noOfDiagonals != 0) {
			int[] currDiagonal = getCurrentDiagonalSorted(matrix, diagonalSize, rowIndex, columnIndex);
			updateSortedDiagonal(currDiagonal, matrix, diagonalSize, rowIndex, columnIndex);
			
			if(rowIndex > 0) {
				rowIndex--;
			} else {
				columnIndex++;
			}
			diagonalSize = Math.min(noOfRows - rowIndex, noOfColumns - columnIndex);
			noOfDiagonals--;
		}
		return matrix;
	}

	public static void print(int[][] rating) {
		System.out.println("\n");
		for(int i = 0; i < rating.length; i++) {
			for(int j = 0; j < rating[0].length; j++) {
				System.out.print(rating[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\n");
	}
	
	public static void updateSortedDiagonal(int[] currDiagonal, int[][] matrix, int diagonalSize, int rowIndex, int columnIndex) {
		for(int i = 0;i < diagonalSize; i++) {
			matrix[rowIndex++][columnIndex++] = currDiagonal[i];
		}
	}

	public static int[] getCurrentDiagonalSorted(int[][] matrix, int diagonalSize, int rowIndex, int columnIndex) {
		int[] currDiagonal = new int[diagonalSize];
		for(int i = 0;i < diagonalSize; i++) {
			currDiagonal[i] = matrix[rowIndex++][columnIndex++];
		}
		sortArray(currDiagonal);
		return currDiagonal;
	}

	public static void sortArray(int[] array) {
		mergeSort(array, 0, array.length - 1);
	}

	public static void mergeSort(int[] array, int beginIndex, int endIndex) {
		if(endIndex <= beginIndex) {
			return;
		}
		int midIndex = getMidIndex(beginIndex, endIndex);
		mergeSort(array, beginIndex, midIndex);
		mergeSort(array, midIndex + 1, endIndex);
		int[] mergedArrays = merge(array, beginIndex, midIndex, endIndex);
		for(int i = 0; i < mergedArrays.length; i++) {
			array[beginIndex + i] = mergedArrays[i];
		}
	}

	public static int[] merge(int[] array, int beginIndex, int midIndex, int endIndex) {
		int[] mergedArrays = new int[endIndex - beginIndex + 1];
		int i = beginIndex, j = midIndex + 1, index = 0;
		while(i <= midIndex && j <= endIndex) {
			if(array[i] < array[j]) {
				mergedArrays[index++] = array[i++];  
			} else {
				mergedArrays[index++] = array[j++]; 
			}
		}
		while(i <= midIndex) {
			mergedArrays[index++] = array[i++];
		}
		while(j <= endIndex) {
			mergedArrays[index++] = array[j++];
		}
		return mergedArrays;
	}

	public static int getMidIndex(int beginIndex, int endIndex) {
		return (endIndex - beginIndex)/2 + beginIndex;
	}

}
