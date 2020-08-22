package arrays;

public class StockButAndSell {

	public static void main(String[] args) {
		int[] a = {1, 5, 3, 8, 12};
		stockBuyAndSell(a, a.length);
	}
	
	
	public static void stockBuyAndSell(int[] a, int n) {
		int maxProfit = 0;
		
		for(int i = 1;i < n; i++) {
			if(a[i] > a[i - 1]) {
				maxProfit += a[i] - a[i - 1];
			}
		}
		
		System.out.println(maxProfit);
	}
}
