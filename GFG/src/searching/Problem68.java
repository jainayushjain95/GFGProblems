package searching;

public class Problem68 {

	public static void main(String[] args) {
		int n = Integer.MAX_VALUE/10;
		
		for(int i = 1; i < n; i = i * 100) {
			long x = System.currentTimeMillis();
			getSqRootFloorEfficient(i);
			long y = System.currentTimeMillis();
			
			long a = System.currentTimeMillis();
			getSqRootFloorNaive(i);
			long b = System.currentTimeMillis();
			System.out.println((y - x) + " , " + (b - a));
		}
		
		
		
	}

	//O(n^0.5), naive solution
	public static int getSqRootFloorNaive(int n) {
		int ans = 0;
		for(int i = 1;i <= n/2; i++) {
			if(i * i <= n) {
				ans = i;
			}
		}
		return ans;
	}
	
	//O(log(n))
	public static int getSqRootFloorEfficient(int n) {
		int ans = 0;
		int start = 1, end = n;
		while(start <= end) {
			int mid = (end - start)/2 + start;
			if(mid * mid == n) {
				ans = mid;
				break;
			} else if(mid * mid < n) {
				ans = mid;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return ans;
	}
	
}
