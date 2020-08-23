package arrays;

public class CheckIfArrayCanBeDividedIntoThreePartsWithEqualSum {

	public static void main(String[] args) {
		int[] a = {10, 4, 14, 14};
		solve(a, a.length);
	}

	public static void solve(int[] a, int n) {
		int[] prefixSUmArray = new int[n];
		prefixSUmArray[0] = a[0];
		for(int i = 1;i < n; i++) {
			prefixSUmArray[i] = prefixSUmArray[i - 1] + a[i];
		}
		
		int i = -1, j = -1;
		
		boolean exists = false;
		
		for(i = 0;i < n - 1 && !exists; i++) {
			int desiredSum = prefixSUmArray[i];
			for(j = i + 1; j < n - 1 && !exists; j++) {
				int leftSum = prefixSUmArray[j] - prefixSUmArray[i];
				int rightSum = prefixSUmArray[n - 1] - prefixSUmArray[j];
				if(leftSum == rightSum && leftSum == desiredSum) {
					exists = true;
				} 
			}
		}
		System.out.println(i + ", " + j);
	}
	
}
