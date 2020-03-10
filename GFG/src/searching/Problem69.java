package searching;

public class Problem69 {

	public static void main(String[] args) {
		int[] a = {2, 5, 8, 12, 30};
		System.out.println(isSumExists(a, 0, a.length - 1, 17));
	}

	public static boolean isSumExists(int[] a, int beginIndex, int endIndex, int sum) {
		if(beginIndex >= endIndex) {
			return false;
		}
		int currSum = a[beginIndex] + a[endIndex];
		if(currSum == sum) {
			return true;
		}
		if(currSum > sum) {
			return isSumExists(a, beginIndex, endIndex - 1, sum);
		} else {
			return isSumExists(a, beginIndex + 1, endIndex, sum);
		}
	}
	
}
