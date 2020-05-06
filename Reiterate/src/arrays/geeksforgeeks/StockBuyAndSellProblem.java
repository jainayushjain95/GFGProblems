package arrays.geeksforgeeks;

public class StockBuyAndSellProblem {

	public static void main(String[] args) {
		int[] a = {1, 5, 3, 8, 12};
		System.out.println(getMaxProfit(a));
	}
	
	public static int getMaxProfit(int[] a) {
		int maxProfit = 0;
		for(int i = 1;i < a.length; i++) {
			if(a[i] > a[i-1]) {
				maxProfit += a[i] - a[i - 1];
			}
		}
		return maxProfit;
	}

}
