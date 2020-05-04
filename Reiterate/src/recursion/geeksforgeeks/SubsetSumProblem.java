package recursion.geeksforgeeks;

public class SubsetSumProblem {

	public static void main(String[] args) {
		int[] input = {10, 20, 30};
		int sum = 30;
		System.out.println(countSubsetsWithGivenSum(input, input.length, sum));
	}

	public static int countSubsetsWithGivenSum(int[] input, int n, int sum) {
		if(n == 0) 
			return (sum == 0) ? 1 : 0;
		
		return countSubsetsWithGivenSum(input, n - 1, sum) + countSubsetsWithGivenSum(input, n - 1, sum - input[n - 1]);
	}
	
}
