package arrays.geeksforgeeks;

public class MaxDiffProblem {

	/*
	 * Max value of a[j] - a[i], where j > i
	 */
	public static void main(String[] args) {
		int[] a = {2, 3, 10, 6, 4, 8, 1};
		System.out.println(getMaxDiff(a));

	}
	
	public static int getMaxDiff(int[] a) {
		int maxDiff = -1, n = a.length;
		int currMax = a[n - 1];
		for(int i = n - 2; i >= 0; i--) {
			maxDiff = Math.max(currMax - a[i], maxDiff);
			currMax = Math.max(currMax, a[i]);
		}
		return maxDiff;
	}

}
