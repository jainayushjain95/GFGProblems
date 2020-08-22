package arrays;

public class MaximumConsecutiveOnes {

	public static void main(String[] args) {
		int[] a = {0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0};
		solve(a, a.length);
	}

	public static void solve(int[] a, int n) {
		int maxCountOfOnes = 0, temp = 0;
		for(int i = 0;i < n; i++) {
			if(a[i] == 1) {
				temp++;
			} else {
				maxCountOfOnes = Math.max(maxCountOfOnes, temp);
				temp = 0;
			}
		}
		System.out.println(maxCountOfOnes);
	}
}
