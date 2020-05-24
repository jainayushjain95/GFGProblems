package arrays;

import java.util.Scanner;

public class LECANDY {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			int N = sc.nextInt();
			int C = sc.nextInt();
			int[] E = new int[N];
			int sum = 0;
			for(int i = 0;i < N;i++) {
				sum += sc.nextInt();
			}
			str.append(solve(N, C, sum) + "\n");
			t--;
		}
		System.out.println(str.toString());
	}
	
	public static String solve(int N, int C, int sum) {
		StringBuilder str = new StringBuilder();
		if(sum <= C) {
			str.append("Yes");
		} else {
			str.append("No");
		}
		return str.toString();
	}

}
