package may.long2020;

import java.util.Scanner;

public class Covid19 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			int n = sc.nextInt();
			int[] X = new int[n];
			for(int i = 0;i < n; i++) {
				X[i] = sc.nextInt();
			}
			solveOptimized(X, n);
			t--;
		}
	}
	
	//O(N)
	public static void solveOptimized(int[] X, int n) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int[] left = new int[n];
		int[] right = new int[n];
		right[n - 1] = 0;
		left[0] = 0;
		int i = n - 1;
		while(i >= 1 && Math.abs(X[i] - X[i - 1]) <= 2) {
			left[n - 1] ++;
			i--;
		}
		min = Math.min(left[n - 1], min);
		max = Math.max(left[n - 1], max);
		
		for(i = n - 2; i >= 0; i--) {
			int disRight = Math.abs(X[i] - X[i + 1]);
			if(disRight <= 2) {
				right[i] = right[i + 1] + 1;
				left[i] = left[i + 1] - 1;
			} else {
				right[i] = 0;
				int j = i;
				while(j >= 1 && Math.abs(X[j] - X[j - 1]) <= 2) {
					left[i] ++;
					j--;
				}
			}
			min = Math.min(right[i] + left[i], min);
			max = Math.max(right[i] + left[i], max);
		}
		//System.out.println((1 + min) + " " + (1 + max));
	}
	
	//O(n^2)
	public static void solve(int[] X, int n) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for(int i = 0;i < n; i++) {
			int count = 1;
			int j = i;
			while(j >= 1) {
				int dis = Math.abs(X[j] - X[j - 1]);
				if(dis <= 2) {
					count++;
				} else {
					j = -1;
				}
				j--;
			}
			j = i;
			while(j < n - 1) {
				int dis = Math.abs(X[j] - X[j + 1]);
				if(dis <= 2) {
					count++;
				} else {
					j = n;
				}
				j++;
			}
			min = Math.min(min, count);
			max = Math.max(max, count);
		}
		
		//System.out.println(min + " " + max);
		
	}

}
