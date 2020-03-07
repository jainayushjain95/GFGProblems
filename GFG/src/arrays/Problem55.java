package arrays;

public class Problem55 {

	public static void main(String[] args) {
		int[] a = {1, 2, 3};
		int[] b = {3, 5, 7};
		System.out.println(getMaxAppearingElement(a, b));
	}
	
	
	public static int getMaxAppearingElement(int[] a, int[] b) {
		int[] rangeSumArray = new int[1 + getMaximumEndRange(a, b)];
		for(int i = 0;i < a.length; i++) {
			rangeSumArray[a[i]] ++;
			if(b[i] + 1 < rangeSumArray.length) {
				rangeSumArray[1 + b[i]] --;
			}
		}
		buildPrefixSum(rangeSumArray);
		int maxIndex = 0;
		for(int i = 0; i < rangeSumArray.length; i++) {
			if(rangeSumArray[maxIndex] < rangeSumArray[i]) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	public static void buildPrefixSum(int[] input) {
		for(int i = 1; i < input.length; i++) {
			input[i] = input[i] + input[i  - 1];
		}
	}
	
	/*
	 * if a = [0, 11, 3, 2], b = [1, 4, 3, 8], it will return 11
	 */
	public static int getMaximumEndRange(int[] a, int [] b) {
		int ans = 0;
		for(int i = 0;i < a.length; i++) {
			if(a[i] > ans) {
				ans = a[i];
			}
			if(b[i] > ans) {
				ans = b[i];
			}
		}
		return ans;
	}

}
