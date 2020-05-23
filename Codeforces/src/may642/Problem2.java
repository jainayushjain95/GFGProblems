package may642;

import java.util.Arrays;
import java.util.Scanner;

public class Problem2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		while(t != 0) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			int[] a = new int[n];
			int[] b = new int[n];
			int aSum = 0;
			for(int i = 0;i < n; i++) {
				a[i] = sc.nextInt();
				aSum += a[i];
			}
			for(int i = 0;i < n; i++) {
				b[i] = sc.nextInt();
			}
			solve(n, k, a, b, aSum);
			t--;
		}

	}

	public static void solve(int n, int k, int[] a, int[] b, int aSum) {
		Arrays.parallelSort(a);
		Arrays.parallelSort(b);
		
		for(int i = 0; i < n && k != 0; i++) {
			if(a[i] < b[n - i - 1]) {
				aSum = aSum - a[i] + b[n - i - 1];
				k--;
			} else {
				break;
			}
			
		}
		System.out.println(aSum);
	}
	
}
