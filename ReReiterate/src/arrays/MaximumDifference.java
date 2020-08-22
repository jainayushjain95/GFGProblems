package arrays;

public class MaximumDifference {

	public static void main(String[] args) {
		int[] a = {30, 10, 8, 2};
		maxDiff(a, a.length);
	}
	
	public static void maxDiff(int[] a, int n) {
		int maxDiff = Integer.MIN_VALUE;
		int leftMost = n - 1;
		
		for(int i = leftMost - 1;i >= 0; i--) {
			maxDiff = Math.max(maxDiff, a[leftMost] - a[i]);
			if(a[i] > a[leftMost]) {
				leftMost = i;
			}
		}
		
		System.out.println(maxDiff);
	}
	
}
