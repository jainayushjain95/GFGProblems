package codechef.lt.april20;

import java.util.Scanner;

public class FFL {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			int n = sc.nextInt();
			int S = sc.nextInt();
			int[] prices = new int[n];
			int[] types = new int[n];

			for(int i = 0;i < n;i ++) {
				prices[i] = sc.nextInt();
			}

			for(int i = 0;i < n;i ++) {
				types[i] = sc.nextInt();
			}
			
			
			int minDef = Integer.MAX_VALUE;
			int minFwd = Integer.MAX_VALUE;
			
			for(int i = 0;i < n; i++) {
				if(types[i] == 0) {
					minDef = Math.min(minDef, prices[i]);
				} else if(types[i] == 1) {
					minFwd = Math.min(minFwd, prices[i]);
				}
			}
			
			if(minFwd == Integer.MAX_VALUE || minDef == Integer.MAX_VALUE) {
				System.out.println("no");
			} else if((minDef + minFwd + S) <= 100) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
			
			t--;
		}

	}

}
