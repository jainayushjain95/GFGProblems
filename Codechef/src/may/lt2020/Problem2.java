package may.lt2020;

import java.util.Scanner;

public class Problem2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			int n = sc.nextInt();
			int[] A = new int[n];
			int[] B = new int[n];
			long weirdDistance = 0;
			
			long aSum = 0, bSum = 0;
			
			for(int i = 0;i < n; i++) {
				A[i] = sc.nextInt();
			}
			for(int i = 0;i < n; i++) {
				B[i] = sc.nextInt();
			}
			
			for(int i = 0;i < n; i++) {
				if(aSum == bSum && A[i] == B[i]) {
					weirdDistance += A[i];
				}
				aSum += A[i];
				bSum += B[i];
			}
			
			str.append(weirdDistance + "\n");
			t--;
		}
		System.out.println(str.toString());
	}

}
