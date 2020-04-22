package codeforces.round636;

import java.util.Scanner;

public class BalancedArray {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			print(sc.nextInt());
			t--;
		}
	}
	
	public static void print(int n) {
		if(n/2 % 2 != 0) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
			int even = 2, sum = 0;
			for(int i = 0; i < n/2; i++) {
				System.out.print(even + " ");
				sum += even;
				even += 2;
			}
			int odd = 1;
			for(int i = n/2; i < n-1; i++) {
				System.out.print(odd + " ");
				sum -= odd;
				odd += 2;
			}
			System.out.println(sum);
		}
	}

}
