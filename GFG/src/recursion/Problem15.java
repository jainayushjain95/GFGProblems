package recursion;

public class Problem15 {

	public static void main(String[] args) {
		System.out.println(tailRecursiveFact(4, 1));
	}

	public static int tailRecursiveFact(int n, int k) {
		if(n <= 1)
			return k;
		return tailRecursiveFact(n-1, n * k);
	}
	
}