package recursion;

public class Problem18 {

	public static void main(String[] args) {
		int n = 51, a = 20, b = 7, c = 15;
		System.out.println(maxNoOfPieces(n, a, b, c));

	}
	
	public static int maxNoOfPieces(int n, int a, int b, int c) {
		if(n == 0) {
			return 0;
		}
		if(canNotCutFurther(n, a, b, c)) {
			return -1;
		}
		
		return 1 + Math.max(Math.max(maxNoOfPieces(n - a, a, b, c), maxNoOfPieces(n - b, a, b, c)), maxNoOfPieces(n - c, a, b, c));
	}
	
	public static boolean canNotCutFurther(int n, int a, int b, int c) {
		return ((n - a) < 0 && (n - b) < 0 && (n - c) < 0);
	}

}
