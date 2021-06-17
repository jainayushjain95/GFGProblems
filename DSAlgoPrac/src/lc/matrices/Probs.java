package lc.matrices;

public class Probs {

	public static void main(String[] args) {
		int[][] matrix = {
				{1, 0, 3},
		};
		new Probs().setZeroes(matrix);
	}
	
	public void setZeroes(int[][] matrix) {
		int rowsCount = matrix.length;
		int columnsCount = matrix[0].length;
		
		boolean isFirstRowZero = false, isFirstColZero = false; 
		
        for(int i = 0;i < rowsCount; i++) {
        	for(int j = 0;j < columnsCount; j++) {
            	if(matrix[i][j] == 0) {
            		if(i == 0) {
            			isFirstRowZero = true;
            		}
            		
            		if(j == 0) {
            			isFirstColZero = true;
            		}
            		
            		matrix[0][j] = 0;
            		matrix[i][0] = 0;
            	}
            }
        }
        
        for(int i = 1;i < rowsCount; i++) {
        	for(int j = 1;j < columnsCount; j++) {
            	if(matrix[i][0] == 0 || matrix[0][j] == 0) {
            		matrix[i][j] = 0;
            	}
            }
        }
        
        if(isFirstRowZero) {
        	for(int i = 0;i < columnsCount; i++) {
        		matrix[0][i] = 0;
        	}
        }
        
        if(isFirstColZero) {
        	for(int i = 0;i < rowsCount; i++) {
        		matrix[i][0] = 0;
        	}
        }
        
    }

}
