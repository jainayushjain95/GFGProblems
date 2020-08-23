package arrays;

public class EqulibriumPointInArray {

	public static void main(String[] args) {
		int[] a = {3, 4, 8, -9, 20, 6};
		solve(a, a.length);
	}

	public static void solve(int[] a, int n) {
		
		int equilibriumIndex = -1;
		int sum = 0;
		for(int x : a) {
			sum += x;
		}
		
		int currLeftSum = 0;
		
		for(int i = 0;i < n && equilibriumIndex == -1; i++) {
			int leftSum = (i == 0) ? 0 : currLeftSum;
			int rightSum = (i == n - 1) ? 0 : sum - currLeftSum - a[i];
			if(leftSum == rightSum) {
				equilibriumIndex = i;
			}
			currLeftSum += a[i];
		}
		
		System.out.println(equilibriumIndex);
		
	}
	
}
