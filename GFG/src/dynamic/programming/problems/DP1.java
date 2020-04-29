package dynamic.programming.problems;

public class DP1 {

	static int MAX = 100;
	static int[] dpArray = new int[MAX + 1];
	
	public static void main(String[] args) {
		int n = 100;
		for(int i = 0;i <= MAX; i++) {
			dpArray[i] = -1;
		}
		long x = System.currentTimeMillis();
		getNthFibonacciNumberDP(n);
		long y = System.currentTimeMillis();
		System.out.println((y - x));
		
		x = System.currentTimeMillis();
		getNthFibonacciNumberRecursive(n);
		y = System.currentTimeMillis();
		System.out.println((y - x));
	}
	
	public static int getNthFibonacciNumberRecursive(int n) {
		if(n == 0 || n == 1) {
			return n;
		}
		return getNthFibonacciNumberRecursive(n - 1) + getNthFibonacciNumberRecursive(n - 2);
	}
	
	//Memoization: Top down
	public static int getNthFibonacciNumberDP(int n) {
		if(dpArray[n] == -1) {
			if(n == 0 || n == 1 ) {
				dpArray[n] = n;
			} else {
				dpArray[n] = getNthFibonacciNumberDP(n - 1) + getNthFibonacciNumberDP(n - 2);
			}
		}
		
		return dpArray[n];
	}
	
	//Tabulation: Bottom Up
	public static int getNthFibonacciNumberDP2(int n) {
		int[] fib = new int[n + 1];
		fib[0] = 0;
		fib[1] = 1;
		for(int i = 2; i <= n; i++) {
			fib[i] = fib[i - 1] + fib[i - 2];
		}
		return fib[n];
	}
}
