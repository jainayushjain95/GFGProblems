package arrays;

public class MaximumSumOfKConsecutiveElements {

	public static void main(String[] args) {
		int[] a = {5, -10, 6, 90, 3};
		int k = 2;
		solve(a, a.length, k);
	}

	public static void solve(int[] a, int n, int k) {
		int noOfWindows = n - k + 1;
		int maxSum = 0;
		for(int i = 0;i < k; i++) {
			maxSum += a[i];
		}
		
		int lastWindowSum = maxSum;
		
		for(int i = 1;i < noOfWindows - 1; i++) {
			lastWindowSum = lastWindowSum - a[i - 1] + a[i + k - 1];
			maxSum = Math.max(lastWindowSum, maxSum);
		}
		
		System.out.println(maxSum);
	}
}
