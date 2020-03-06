package arrays;

public class Problem51 {

	public static void main(String[] args) {
		int[] a = {1, 4, 20, 3, 10, 5};
		System.out.println(isSubarrayWithGivenSumExists(a, 431));
	}

	public static boolean isSubarrayWithGivenSumExists(int[] a, int sum) {
		int n = a.length;
		int start = 0, end = 0, windowSum = 0;
		while(end < n && start < n) {
			if(windowSum < sum) {
				windowSum += a[end];
				end++;
			} else if(windowSum == sum) {
				return true;
			} else {
				windowSum -= a[start];
				start++;
			}
		}
		return false;
	}

}
