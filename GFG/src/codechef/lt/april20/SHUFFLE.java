package codechef.lt.april20;

import java.util.Scanner;

public class SHUFFLE {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			int[] a = new int[n];
			for(int i = 0;i < n; i++) {
				a[i] = sc.nextInt();
			}
			check(a, k);
			t--;
		}
	}
	
	public static void check(int[] a, int k) {
		int n = a.length;
		boolean flag = true;
		for(int i = n; i > 0; i = i - k) {
			int j = 0;
			for(; j < i - k; j++) {
				if(a[j] > a[j + k]) {
					swap(a, j, j + k);
				}
			}
			for(; j < i - 1; j++) {
				if(a[j] > a[j + 1]) {
					flag = false;
					break;
				}
			}
			if(j < n - 1) {
				if(a[j] > a[j + 1]) {
					flag = false;
					break;
				}
			}
			if(!flag) {
				break;
			}
		}
		if(flag) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}
	
	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}
