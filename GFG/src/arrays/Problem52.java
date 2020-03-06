package arrays;

public class Problem52 {

	public static void main(String[] args) {
		int m = 15, n = 5;
		printNBonnaciNumbers(m, n);
	}
	
	//O(N), O(M)
	public static void printNBonnaciNumbers(int m, int n) {
		int[] auxillaryArray = new int[m];
		auxillaryArray[n - 1] = 1;
		auxillaryArray[n] = 1;
		
		for(int i = n + 1; i < m; i++) {
			auxillaryArray[i] = 2 * auxillaryArray[i - 1] - auxillaryArray[i - n - 1];
		}
		
		for(int x : auxillaryArray) {
			System.out.println(x);
		}
	}

}
