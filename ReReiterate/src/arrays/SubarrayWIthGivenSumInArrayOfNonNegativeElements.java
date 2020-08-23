package arrays;

public class SubarrayWIthGivenSumInArrayOfNonNegativeElements {

	public static void main(String[] args) {
		int[] a = {1, 4, 0, 0, 3, 10, 5};
		int sum = 19;
		solve(a, a.length, sum);
	}
	
	public static void solve(int[] a, int n, int sum) {
		int currSum = 0;
		boolean sumExists = false;

		int i = 0, j = 0;
		
		while(i < n && j < n) {
			while(j < n && currSum < sum) {
				currSum += a[j];
				j++;
			}
			while(i < n && currSum > sum) {
				currSum -= a[i];
				i++;
			}
			if(sum == currSum) {
				sumExists = true;
				break;
			}
		}
		
		System.out.println(sumExists);
	}

}
