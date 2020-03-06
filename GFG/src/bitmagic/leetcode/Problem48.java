package bitmagic.leetcode;

public class Problem48 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static int findMissing(int[] a) {
		int output = a[0];
		for(int i = 1;i < a.length; i++) {
			output = output ^ a[i];
		}
		return output;
	}

}
