package lc.matrices;

public class RotateImage {

	public static void main(String[] args) {
		int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
		rotateSolve(matrix);
		for(int i = 0;i < matrix.length; i++) {
			for(int j = 0;j < matrix.length; j++) {
				System.out.print(matrix[i][j] + ", ");
			}
			System.out.println();
		}
	}

	public static void rotateSolve(int[][] matrix) {
        int rowsCount = matrix.length;
        int rowIndex = 0, columnIndex = 0;
        int newElement = -1, oldElement = matrix[rowIndex][columnIndex];

        int count = 0;
        while(count < rowsCount*rowsCount) {
        	int oldRowIndex = rowIndex;
        	rowIndex = columnIndex;
        	columnIndex = getNewColumnIndex(oldRowIndex, rowsCount);
        	newElement = matrix[rowIndex][columnIndex];
        	matrix[rowIndex][columnIndex] = oldElement;
        	oldElement = newElement;
        	count++;
        }
        
    }
	
	public static int getNewColumnIndex(int rowIndex, int rowsCount) {
		return rowsCount - 1 - rowIndex;
	}
}
