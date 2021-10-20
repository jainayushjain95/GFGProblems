package SepCookOff2021;

import java.io.*;
import java.util.*;

public class Main {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int t = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		while(t != 0) {
			int N = sc.nextInt();
			int[] A = new int[N];
			int oddIndex = -1, evenIndex = -1;
			for(int i = 0;i < N; i++) {
				A[i] = sc.nextInt();
				if(evenIndex == -1 && A[i] % 2 == 0) {
					evenIndex = i;
				} else if(oddIndex == -1 && A[i] % 2 != 0) {
					oddIndex = i;
				}
			}
			if(oddIndex == -1 || evenIndex == -1) {
				sb.append("-1");	
			} else {
				sb.append(A[oddIndex] + " ");
				for(int i = 0;i < N; i++) {
					if(i != oddIndex && i != evenIndex) {
						sb.append(A[i] + " ");
					} 
				}
				sb.append(A[evenIndex] + " ");
			}
			sb.append("\n");
			t--;
		}
		System.out.println(sb.toString());
	}

}
