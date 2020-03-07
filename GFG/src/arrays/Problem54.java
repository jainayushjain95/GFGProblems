package arrays;

public class Problem54 {

	public static void main(String[] args) {
		int[] a = {4, 2, -2};
		System.out.println(hasEquilibriumPoint(a));
	}
	
	public static boolean hasEquilibriumPoint(int[] a) {
		boolean output = false;
		int totalSum = 0;
		for(int x : a) {
			totalSum += x;
		}
		int lSum = 0;
		for(int i = 0; i < a.length; i++) {
			if(lSum == totalSum - a[i]) {
				output = true;
				break;
			}
			lSum += a[i];
			totalSum -= a[i];
		}
		return output;
	}

}
