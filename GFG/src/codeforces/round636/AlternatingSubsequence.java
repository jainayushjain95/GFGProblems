package codeforces.round636;

import java.util.Scanner;

public class AlternatingSubsequence {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			int n = sc.nextInt();
			int[] a = new int[n];
			for(int i = 0;i < n; i++) {
				a[i] = sc.nextInt();
			}
			print(a, n);
			t--;
		}

	}

	public static void print(int[] a, int n) {
		int i = 0; long sum = 0;

		while(i < n) {
			if(a[i] < 0) {
				int max = a[i];
				while(i < n && a[i] < 0) {
					max = Math.max(max, a[i]);
					i++;
				}

				sum += max;

			}

			if(i < n && a[i] > 0) {
				int max = a[i];

				while(i < n && a[i] > 0) {
					max = Math.max(max, a[i]);
					i++;
				}
				sum += max;
			}

		}
		System.out.println(sum);
	}

}
