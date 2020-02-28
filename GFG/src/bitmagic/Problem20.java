package bitmagic;

public class Problem20 {
	public static void main(String[] args) {
		printAllSubsets("ABC");
	}
	
	public static void printAllSubsets(String input) {
		int n = input.length();
		int total = (int)Math.pow(2, n);
		for(int i = 0;i < total; i++) {
			for(int j = 1; j <= n; j++) {
				if(isKthBitSet(i, j)) {
					System.out.print(input.charAt(j-1));
				}
			}
			System.out.println();
		}
	}
	public static boolean isKthBitSet(int n, int k) {
		return (n & (1 << (k-1))) != 0;
	}
}
