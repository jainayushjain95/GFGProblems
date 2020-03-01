package recursion;

public class Problem26 {

	public static void main(String[] args) {
		int n = 8, k = 2;
		System.out.println(josephusProblem(n, k));
	}

	public static int josephusProblem(int n, int k) {
		if(n == 1)
			return 0;
		return (josephusProblem(n-1, k) + k) % n;
	}
	
}
