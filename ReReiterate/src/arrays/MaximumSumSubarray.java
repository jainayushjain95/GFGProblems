package arrays;

public class MaximumSumSubarray {

	public static void main(String[] args) {
		int[] a = {-6, -1, -8};
		maximumSumSubarray(a, a.length);
	}

	public static void maximumSumSubarray(int[] a, int n) {
		int maxSum = a[0],  temp = a[0];
		
		for(int i = 1;i < n; i++) {
			temp = ((temp + a[i]) > a[i]) ? (temp + a[i]) : a[i];
			maxSum = Math.max(maxSum, temp);
		}
		
		System.out.println(maxSum);
	}
	
}
