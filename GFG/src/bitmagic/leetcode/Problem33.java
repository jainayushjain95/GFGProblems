package bitmagic.leetcode;

public class Problem33 {

	public static void main(String[] args) {
		int[] output = noOfSetBitsArray(2);
		for(int x : output) {
			System.out.println(x);
		}

	}
	
	public static int[] noOfSetBitsArray(int n) {
		int[] output = new int[n+1];
		int index = 1, size = 1;
		for(int i = 1; i <= n;) {
			if(i == 1) {
				output[i] = 1;
				i++;
			} else {
				int j = index;
				for(int k = 0;i <= n && k < size; k++) {
					output[i] = output[j];
					j++;
					i++;
				}
				j = index;
				for(int k = 0;i <= n && k < size; k++) {
					output[i] = 1 + output[j];
					j++;
					i++;
				}
				index = index * 2;
				size = size * 2;
			}
		}
		return output;
	}
	
}
