package arrays.geeksforgeeks;

public class MaxEvenOddSubarrayLength {

	public static void main(String[] args) {
		int[] a = {7, 10, 12, 14, 7, 8};
		solve(a);
	}

	public static void solve(int[] a) {
		int maxLength = 1, curr = 1;
		for(int i = 1; i < a.length; i++) {
			if(a[i] % 2 != a[i - 1] % 2) {
				curr++;
				maxLength = Math.max(maxLength, curr);
			} else {
				curr = 1;
			}
		}
		System.out.println(maxLength);
	}
	
}
