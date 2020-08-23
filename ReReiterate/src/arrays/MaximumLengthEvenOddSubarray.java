package arrays;

public class MaximumLengthEvenOddSubarray {

	public static void main(String[] args) {
		int[] a = {10, 12, 13};
		solve(a, a.length);
	}
	
	public static void solve(int[] a, int n) {
		int maxLength = 1, tempLength = 1;
		boolean parity = (a[0] % 2) == 0;
		
		for(int i = 1;i < n; i++) {
			boolean currParity = (a[i] % 2) == 0;
			if(currParity != parity) {
				tempLength++;
				parity = currParity;
				maxLength = Math.max(maxLength, tempLength);
			} else {
				parity = currParity;
				tempLength = 1;
			}
		}
		System.out.println(maxLength);
	}
}
