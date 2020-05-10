package arrays.geeksforgeeks;

public class MaximumSumSubarray {

	public static void main(String[] args) {
		int[] a = {2, 3, -8, 7, -1, 2, 3};
		solve(a);
	}
	
	public static void solve(int[] a) {
		int maxSubarraySum = 0;
		for(int i = 0;i < a.length; i++) {
			maxSubarraySum = Math.max(maxSubarraySum + a[i], a[i]);
		}
		System.out.println(maxSubarraySum);
	}

}
