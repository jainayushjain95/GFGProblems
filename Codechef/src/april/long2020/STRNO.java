package april.long2020;

import java.util.Scanner;

public class STRNO {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			int X = sc.nextInt();
			int K = sc.nextInt();
			solve(X, K);
			t--;
		}
	}

	public static void solve(int X, int K) {
		int count = prime(X);
		if(count >= K) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	public static int prime(int n) {
		int primesCount = 0;
		while(n % 2 == 0) {
			n = n / 2;
			primesCount++;
		}
		for(int i = 3;i <= Math.sqrt(n); i = i + 2) {
			while(n % i == 0) {
				n = n / i;
				primesCount++;
			}
		}
		if(n > 2) {
			primesCount++;
		}
		return primesCount;
	}
}
