package binarysearch.geeksforgeeks;

public class FloorOfSquareRootOfANumber {

	public static void main(String[] args) {
		long n = 8;
		solve(n);
	}
	
	public static void solve(long n) {
		long ans = 0;
		if(n <= 1) {
			ans = 1;
		} else {
			long start = 1, end = n;
			while(start <= end) {
				long mid = getMidIndex(start, end);
				if(mid * mid == n) {
					ans = mid;
					start = end;
				} else if(mid * mid < n) {
					start = mid + 1;
					ans = mid;
				} else {
					end = mid - 1;
				}
			}
		}
		System.out.println(ans);
	}
	
	public static long getMidIndex(long i, long j) {
		return i + (j - i)/2;
	}
	
}
