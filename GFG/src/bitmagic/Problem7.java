package bitmagic;

public class Problem7 {

	public static void main(String[] args) {
		generateSubsets("asrdfgukuwaeiurbc");
	}
	
	public static void generateSubsets(String input) {
		int n = input.length();
		int total = (int)Math.pow(2, n);
		
		for(int i = 0;i < total; i++) {
			for(int j = 1; j <= n; j++) {
				if(isKthBitSet(i, j)) {
					System.out.print(input.charAt(j-1) + ", ");
				}
			}
			System.out.println();
		}
	}
	
	public static boolean isKthBitSet(int n, int k) {
		return (n & (1 << (k-1))) != 0;
	}

}
