package may.lt2020;

import java.util.Scanner;

public class Problem4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			int N = sc.nextInt();
			int Q = sc.nextInt();
			int[] A = new int[N];
			int[] edgesArrayLeft = new int[N];
			int[] edgesArrayRight = new int[N];
			for(int i = 0;i < N; i++) {
				A[i] = sc.nextInt();
			}
			
			for(int i = 0;i < N; i++) {
				edgesArrayLeft[i] = sc.nextInt();
			}
			
			for(int i = 0;i < N; i++) {
				edgesArrayRight[i] = sc.nextInt();
			}
			
			for(int i = 0;i < Q; i++) {
				str.append(solve(N, A, edgesArrayLeft, edgesArrayRight, sc.nextInt(), sc.nextInt()) + "\n");
			}
			
			t--;
		}
		System.out.println(str.toString());
	}
	
	public static String solve(int N, int[] A, int[] edgesArrayLeft, int[] edgesArrayRight, int qa, int qb) {
		StringBuilder str = new StringBuilder();
		return str.toString();
	}
}
