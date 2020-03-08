package arrays;

public class Problem56 {

	public static void main(String[] args) {
		int[] a = {1, 1, 1, 1, 1, 52};
		System.out.println(canBeDividedIntoTwoSubarrays(a));
	}
	
	public static boolean canBeDividedIntoTwoSubarrays(int[] a) {
		boolean output = false;
		int totalSum = getTotalSum(a);
		int currSum = a[0];
		for(int i = 1;i < a.length; i++) {
			if(currSum * 2 == totalSum) {
				output = true;
				break;
			}
			currSum += a[i];
		}
		return output;
	}
	
	public static int getTotalSum(int[] a) {
		int sum = 0;
		for(int x : a) {
			sum += x;
		}
		return sum;
	}

}
