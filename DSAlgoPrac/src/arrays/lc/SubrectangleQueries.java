package arrays.lc;

public class SubrectangleQueries {

	public static void main(String[] args) {
		int[] height = {4,2,0,3,2,5};
		System.out.println(trapSolve(height));
	}

	public static int trapSolve(int[] height) {
		int total = 0;
		int[] lMax = getLMaxArray(height);
		int[] rMax = getRMaxArray(height);
		
		for(int i = 1;i < height.length - 1; i++) {
			if(lMax[i] >= 0 && rMax[i] >= 0) {
				total = total + Math.min(lMax[i], rMax[i]) - height[i];
			}
		}
		
		return total;
	}

	public static int[] getLMaxArray(int[] height) {
		int[] lMax = new int[height.length];
		int max = height[0];
		lMax[0] = -1;
		
		for(int i = 1;i < height.length; i++) {
			int currHeight = height[i];
			if(max > currHeight) {
				lMax[i] = max;
			} else {
				lMax[i] = -1;
			}
			max = Math.max(max, currHeight);
		}
		
		return lMax;
	}
	
	public static int[] getRMaxArray(int[] height) {
		int[] rMax = new int[height.length];
		int max = height[height.length - 1];
		rMax[height.length - 1] = -1;
		
		for(int i = height.length - 2;i >= 0; i--) {
			int currHeight = height[i];
			if(max > currHeight) {
				rMax[i] = max;
			} else {
				rMax[i] = -1;
			}
			max = Math.max(max, currHeight);
		}
		
		return rMax;
	}
	
}
