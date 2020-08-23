package arrays;

public class MaximumAppearingElementInNRanges {

	public static void main(String[] args) {
		int[] L = {1, 2, 5, 15};
		int[] R = {5, 8, 7, 18};
		solve(L, R);
	}
	
	public static void solve(int[] L, int[] R) {
		int maxFreqIndex = 0;
		int maxR = -1;
		for(int x : R) {
			maxR = Math.max(maxR, x);
		}
		int[] a = new int[maxR + 2];
		
		for(int i = 0;i < L.length; i++) {
			a[L[i]]++;
			a[R[i] + 1]--;
		}
		
		for(int i = 1; i <= maxR; i++) {
			a[i] = a[i] + a[i - 1];
			if(a[i] > a[maxFreqIndex]) {
				maxFreqIndex = i;
			}
		}
		
		System.out.println(maxFreqIndex);
	}

}
