package recursion;

public class Problem30 {

	public static long mod = 1000000007;
	
	public static void main(String[] args) {
		System.out.println(pow(3, 2));

	}
	
	public static long pow(int n, int r) {
		if(n == 1 || r == 0)
			return 1;
		if(r == 1)
			return n;
		
		return (n * pow(n, r - 1)) % mod;
	}

}
