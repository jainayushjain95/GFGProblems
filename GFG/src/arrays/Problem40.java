package arrays;

public class Problem40 {

	public static void main(String[] args) {
		int[] input = {1, 5, 3, 8, 12};
//		int[] input = {30, 20, 10};
		System.out.println(getMaxProfit1(input));
	}
	
	//O(N), O(1)
	public static int getMaxProfit1(int[] a) {
		int maxprofit = 0;
		int n = a.length;
		for(int i = 1; i < n; i++) {
			if(a[i] > a[i - 1]) {
				maxprofit += a[i] - a[i - 1];
			}
		}
		return maxprofit;
	}

}
