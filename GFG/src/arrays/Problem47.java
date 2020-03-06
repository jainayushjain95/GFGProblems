package arrays;

public class Problem47 {

	public static void main(String[] args) {
		int[] a = {5, -2, 3, 4};
		System.out.println(maxSumCircularSubarray(a));
	}

	public static int maxSumCircularSubarray(int[] a) {
		int x = maxSubarraySum(a);
		int y = getTotalSum(a);
		int z = minSubarraySum(a);
		return Math.max(x, y-z);
	}

	//kadane's algorithm
	public static int maxSubarraySum(int [] a) {
		int maxSubarraySum = a[0], maxEnding = a[0];
		int n = a.length;

		for(int i = 1;i < n; i++) {
			maxEnding = Math.max(a[i], a[i] + maxEnding);
			maxSubarraySum = Math.max(maxEnding, maxSubarraySum);
		}
		return maxSubarraySum;
	}

	//kadane's algorithm
	public static int minSubarraySum(int [] a) {
		int minSubarraySum = a[0], minEnding = a[0];
		int n = a.length;

		for(int i = 1;i < n; i++) {
			minEnding = Math.min(a[i], a[i] + minEnding);
			minSubarraySum = Math.min(minSubarraySum, minEnding);
		}
		return minSubarraySum;
	}

	public static int getTotalSum(int[] a) {
		int sum = 0;
		for(int x : a) {
			sum += x;
		}
		return sum;
	}
	
}
