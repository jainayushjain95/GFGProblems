package may640;

import java.util.Scanner;

public class Problem3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			solve(n, k);
			t--;
		}
	}
	
	public static void solve(int n, int k) {
		int p = (int)(Math.ceil((double)k / (n - 1)));
		int k1 = (p - 1) * (n - 1);
		int k2 = k - k1;
		int beginIndex = (p - 1) * n;
		int output = beginIndex + k2;
		
		System.out.println(output);
	}

}
