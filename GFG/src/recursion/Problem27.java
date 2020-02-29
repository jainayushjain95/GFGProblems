package recursion;

public class Problem27 {

	public static void main(String[] args) {
		int[] input = {10, 20, 15};
		int sum = 25;
		System.out.println(countOfSubsetsSum(input, sum, input.length));
	}
	
	public static int countOfSubsetsSum(int[] input, int sum, int index) {
		if(index == 0) {
			return (sum == 0) ? 1 : 0;
		}
		return countOfSubsetsSum(input, sum - input[index-1], index - 1) + countOfSubsetsSum(input, sum, index - 1);
	}

}
