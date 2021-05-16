package lc.bs;

public class Search2DMatrix {

	public static void main(String[] args) {
		int[][] matrix = {
				{1, 3, 5}
		};
		int target = 5;
		System.out.println(searchMatrixSolve(matrix, target, 0, matrix.length * matrix[0].length));
	}
	
	public static boolean searchMatrixSolve(int[][] matrix, int target, int beginIndex, int endIndex) {
        if(beginIndex > endIndex) {
        	return false;
        }
        
        int midIndex = getMidIndex(beginIndex, endIndex);
        int rowIndex = getRowIndex(midIndex, matrix[0].length);
        int columnIndex = getColumnIndex(midIndex, matrix[0].length);
        
        if(rowIndex >= matrix.length || columnIndex >= matrix[0].length) {
        	return false;
        }
        
        if(matrix[rowIndex][columnIndex] == target) {
        	return true;
        } else if(matrix[rowIndex][columnIndex] > target) {
        	return searchMatrixSolve(matrix, target, beginIndex, midIndex - 1);
        } else {
        	return searchMatrixSolve(matrix, target, midIndex + 1, endIndex);
        }
    }
	
	public static int getMidIndex(int beginIndex, int endIndex) {
		return (endIndex - beginIndex)/2 + beginIndex;
	}
	
	public static int getRowIndex(int midIndex, int noOfColumns) {
		return midIndex / noOfColumns;
	}
	
	public static int getColumnIndex(int midIndex, int noOfColumns) {
		return midIndex % noOfColumns;
	}

}
