package july648;

import java.util.Scanner;

public class Problem2 {

	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			t--;
			int n = sc.nextInt();
			int[] a = new int[n];
			int[] ps = new int[n];
			a[0] = sc.nextInt();
			if(a[0] == 1) {
				ps[0]++;
			}
			for(int i = 1;i < n; i++) {
				a[i] = sc.nextInt();
				if(a[i] == 1) {
					ps[i] = 1 + ps[i - 1];
				} else {
					ps[i] = ps[i - 1];
				}
			}
			solve(n, a, ps);
			
		}
		System.out.println(str.toString());
	}

	public static void solve(int n, int[] a, int[] ps) {
		int turn = 0;
		for(int i = 0;i < n - 1; i++) {
			turn++;
			if(a[i] != 1) {
				int onesCount = ps[n - 2] - ps[i];
				if(onesCount % 2 != 0) {
					turn++;
				}
			}
		}
		System.out.println(turn);
	}
	
}
