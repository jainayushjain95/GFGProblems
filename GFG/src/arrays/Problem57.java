package arrays;

public class Problem57 {

	public static void main(String[] args) {
		int[] a = {1, 3, 4, 0, 4}; 
		System.out.println(canBeDividedIntoThreeSubarrays(a));
	}


	public static boolean canBeDividedIntoThreeSubarrays(int[] a) {
		int totalSum = getTotalSum(a);
		int currSum = a[0];
		int index1 = -1, index2 = -1, sum1 = totalSum/3, sum2 = 2*totalSum/3;
		if(totalSum % 3 == 0) {
			for(int i = 1;i < a.length; i++) {
				if(currSum == sum1 && index1 == -1) {
					index1 = i - 1;
				} else if(currSum == sum2 && index2 == -1) {
					index2 = i - 1;
					break;
				}
				currSum += a[i];
			}
		}
		System.out.println(index1 + ", " + index2);
		return index1 != -1 && index2 != -1;
	}

	public static int getTotalSum(int[] a) {
		int sum = 0;
		for(int x : a) {
			sum += x;
		}
		return sum;
	}

}
