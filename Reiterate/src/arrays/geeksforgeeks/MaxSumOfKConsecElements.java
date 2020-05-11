package arrays.geeksforgeeks;

public class MaxSumOfKConsecElements {

	public static void main(String[] args) {
		int[] a = {5, -10, 6, 90, 3};
		int k = 2;
		solve(a, k);
	}

	public static void solve(int[] a, int k) {
		int maxSum = 0, lastWindowSum = 0;
		for(int i = 0;i < k; i++) {
			maxSum += a[i];
		}
		lastWindowSum = maxSum;
		for(int i = 1;i < a.length - k + 1; i++) {
			lastWindowSum = lastWindowSum + a[i + k - 1] - a[i - 1];
			maxSum = Math.max(lastWindowSum, maxSum);
		}
		System.out.println(maxSum);
	}

}
