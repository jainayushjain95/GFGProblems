package arrays;

public class Problem50 {

	public static void main(String[] args) {
		int[] a = {1, 8, 30, -5, 20, 7};
		System.out.println(maxSumOfKConsecElements(a, 3));
	}
	
	public static int maxSumOfKConsecElements(int[] a, int k) {
		int output = getSumOfKElements(a, k);
		int curr = output;
		int i = 1, j = k, n = a.length;
		while(j+1 < n) {
			curr = curr - a[i - 1] + a[j];
			output = Math.max(output, curr);
			i++;
			j++;
		}
		return output;
	}

	public static int getSumOfKElements(int[] a, int k) {
		int sum = 0;
		for(int i = 0; i < k; i++) {
			sum += a[i];
		}
		return sum;
	}
	
}
