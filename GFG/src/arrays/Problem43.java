package arrays;

public class Problem43 {

	public static void main(String[] args) {
		int[] a = {2, 3, -8, 7, -1, 2, 3};
		System.out.println(maxContiguosArraySum(a));
	}
	
	public static int maxContiguosArraySum(int[] a) {
		int output = a[0];
		for(int i = 1; i < a.length; i++) {
			output = Math.max(a[i], output + a[i]);
		}
		return output;
	}

}
