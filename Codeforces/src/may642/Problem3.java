package may642;

import java.util.Scanner;

public class Problem3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		while(t != 0) {
			int n = sc.nextInt();
			solve(n);
			t--;
		}
	}

	public static void solve(int n) {
		if(n == 1) {
			System.out.println(0);
			return;
		}
		long minMoves = 0;
		long cycles = n/2;
		long i = 1;
		
		while(i <= cycles) {
			minMoves += i*8*i;
			i++;
		}
		
		System.out.println(minMoves);
	}
	
}
