package lc.google;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

	public static void main(String[] args) {
		int[][] matrix = {
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{9, 10, 11, 12}
		};
		System.out.println(new SpiralMatrix().spiralOrder(matrix));
	}
	
	 public List<Integer> spiralOrder(int[][] matrix) {
	        int rowsCount = matrix.length, colsCount = matrix[0].length;
	        int left = 0, right = colsCount - 1, top = 0, bottom = rowsCount - 1;
	        int total = rowsCount * colsCount;
	        List<Integer> output = new ArrayList<>();
	        
	        while(total != 0) {
	            for(int i = left; total > 0 && i <= right; i++) {
	                output.add(matrix[top][i]);
	                total--;
	            }
	            top++;
	            
	            for(int i = top; total > 0 && i <= bottom; i++) {
	                output.add(matrix[i][right]);
	                total--;
	            }
	            right--;
	            
	            for(int i = right; total > 0 && i >= left; i--) {
	                output.add(matrix[bottom][i]);
	                total--;
	            }
	            bottom--; 
	            
	            for(int i = bottom; total > 0 && i >= top; i--) {
	                output.add(matrix[i][left]);
	                total--;
	            }
	            left++;
	        }
	        return output;
	    }

}
