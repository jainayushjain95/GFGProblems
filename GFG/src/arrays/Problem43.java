package arrays;

public class Problem43 {

	public static void main(String[] args) {
		int[] a = {2, 3, -8, 7, -1, 2, 3};
		System.out.println(maxContiguosArraySum(a));
	}
	
	public static int maxContiguosArraySum(int[] a) {
		int maxEnding = a[0], output = a[0];
		for(int i = 1; i < a.length; i++) {
			maxEnding = Math.max(a[i], maxEnding + a[i]);
			output = Math.max(output, maxEnding);
		}
		return output;
	}

}
