package arrays;

public class Problem46 {

	public static void main(String[] args) {
		int[] a = {72, 10, 132, 14};
		System.out.println(lengthOfLongestEvenOddSubarray(a));
	}

	public static int lengthOfLongestEvenOddSubarray(int[] a) {
		int output = 1;
		boolean isLastEven = a[0] % 2 == 0;
		for(int i = 1; i < a.length; i++) {
			if(isLastEven && a[i] % 2 == 0) {
				output = 1;
			} else if(!isLastEven && a[i] % 2 != 0) {
				output = 1;
			} else {
				output++;
				isLastEven = !isLastEven;
			}
		}
		return output;
	}
	
}
