package arrays.geeksforgeeks;

public class SubarraySumExistsWithOnlyNonNegativeElements {

	public static void main(String[] args) {
		int[] a = {1, 4, 20, 3, 10, 5};
		int sum = 15;
		solve(a, sum);
	}
	
	public static void solve(int[] a, int sum) {
		int start = 0, end, currSum = a[0];
		for(end = 1;end <= a.length; end++) {
			while(start < endss - 1 && currSum > sum) {
				currSum -= a[start++];
			}
			if(currSum == sum) {
				System.out.println("YES");
				return;
			}
			if(end < a.length) {
				currSum += a[end];
			}
		}
		System.out.println("NO");
	}

}
