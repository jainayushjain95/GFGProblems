package arrays;

public class Problem39 {

	public static void main(String[] args) {
		int[] input = {2, 3, 1, 6, 4, -8, 1};
		System.out.println(maximumDifferencePair(input));
	}

	public static int maximumDifferencePair(int[] input) {
		int currentMaxDiff = input[1] - input[0], currMin = input[0];
		for(int i = 1; i < input.length; i++) {
			currentMaxDiff = Math.max(currentMaxDiff, input[i] - currMin);
			if(input[i] < currMin) {
				currMin = input[i];
			}
		}
		return currentMaxDiff;
	}
	
}
