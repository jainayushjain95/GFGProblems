package july648;

import java.util.HashSet;
import java.util.Scanner;

public class Problem1 {

	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			t--;
			int n = sc.nextInt();
			int m = sc.nextInt();
			int[] a = new int[n];
			int[] b = new int[m];
			HashSet<Integer> hs = new HashSet<Integer>();
			for(int i = 0;i < n; i++) {
				a[i] = sc.nextInt();
				hs.add(a[i]);
			}
			int common = -1;
			for(int i = 0;i < m; i++) {
				b[i] = sc.nextInt();
				if(hs.contains(b[i])) {
					common = b[i];
				}
			}
			if(common != -1) {
				str.append("YES" + "\n" + "1 " + common + "\n");
			} else {
				str.append("NO" + "\n");
			}
		}
		System.out.println(str.toString());
	}

	public static String solve(int n, int m, int[] a, int[] b) {
		String solution = "";
		
		return solution;
	}
	
}
