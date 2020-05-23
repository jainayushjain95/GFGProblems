package may642;

import java.util.Scanner;

public class Problem1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		while(t != 0) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			solve(n, m);
			t--;
		}
	}

	public static void solve(int n, int m) {
		if(n == 1) {
			System.out.println(0);
			return;
		} else if(n == 2) {
			System.out.println(m);
			return;
		}
		int zeroesAllowed = (int)(Math.ceil(n/2.0));
		int bestSum = 0;
		int slotsLeft = n - zeroesAllowed;
		
		int div = m / slotsLeft;
		int rem = m % slotsLeft;
		
		if(rem == 0) {
			bestSum = 2 * (div * slotsLeft);
		} else {
			bestSum = 2 * ((div * (slotsLeft - 1)) + ((div + rem)));
		}
		
		System.out.println(bestSum);
	}
	
}
