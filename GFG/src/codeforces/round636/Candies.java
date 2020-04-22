package codeforces.round636;

import java.util.Scanner;

public class Candies {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			System.out.println(getX(sc.nextInt()));
			t--;
		}
	}
	
	public static int getX(int n) {
		int divisor = 2, rem = -1, x = -1;
		
		while(rem != 0) {
			divisor *= 2;
			rem = n % (divisor - 1);
			x = n/(divisor - 1);
		}
		return x;
	}

}
