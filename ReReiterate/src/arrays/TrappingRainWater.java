package arrays;

public class TrappingRainWater {

	public static void main(String[] args) {
		int[] a = {2, 0, 2};
		solve(a, a.length);	
	}
	
	public static void solve(int[] a, int n) {
		int[] lga = getLeftMaxGreaterArray(a, n);
		int[] rga = getRightMaxGreaterArray(a, n);
		
		int qty = 0;
		
		for(int i = 1;i < n - 1;i ++) {
			if(lga[i] > -1 && rga[i] > -1) {
				qty += Math.min(lga[i], rga[i]) - a[i];	
			}
		}
		System.out.println(qty);
	}
	
	public static int[] getLeftMaxGreaterArray(int[] a, int n) {
		int[] lga = new int[n];
		lga[0] = -1;
		int max = a[0];
		
		for(int i = 1;i < n; i++) {
			if(max > a[i]) {
				lga[i] = max;
			} else {
				lga[i] = -1;
			}
			max = Math.max(max, a[i]);
		}

		return lga;
	}
	
	public static int[] getRightMaxGreaterArray(int[] a, int n) {
		int[] rga = new int[n];
		rga[n - 1] = -1;
		int max = a[n - 1];
		
		for(int i = n - 2;i >= 0; i--) {
			if(max > a[i]) {
				rga[i] = max;
			} else {
				rga[i] = -1;
			}
			max = Math.max(max, a[i]);
		}
		return rga;
	}
	
	public static void print(int[] a) {
		for(int x : a) {
			System.out.println(x);
		}
	}
	
}
