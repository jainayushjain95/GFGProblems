package arrays.geeksforgeeks;

public class MaximumConsecutive1s {

	public static void main(String[] args) {
		int[] a = {1, 0, 1, 1, 0, 1, 0, 1, 1};
		solve(a);
	}
	
	public static void solve(int[] a) {
		int maxConsecutiveOnes = 0;
		for(int i = 0 ;i < a.length; i++) {
			int currMax = 0;
			while(i < a.length && a[i] == 1) {
				currMax++;
				i++;
			}
			maxConsecutiveOnes = Math.max(maxConsecutiveOnes, currMax);
		}
		System.out.println(maxConsecutiveOnes);
	}

}
