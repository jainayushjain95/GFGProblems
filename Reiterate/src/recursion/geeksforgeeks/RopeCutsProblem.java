package recursion.geeksforgeeks;

public class RopeCutsProblem {

	public static void main(String[] args) {
		System.out.println(getMaxPieces(9, 3, 2, 2));
	}

	public static int getMaxPieces(int n, int a, int b, int c) {
		if(n < 0) {
			return -1;
		}
		if(n == 0) {
			return 0;
		}
		int res = Math.max(getMaxPieces(n - a, a, b, c), Math.max(getMaxPieces(n - b, a, b, c), getMaxPieces(n - c, a, b, c)));
		return (res == -1) ? -1 : 1 + res; 
	}
	
}