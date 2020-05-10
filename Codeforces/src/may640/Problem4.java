package may640;

import java.util.Scanner;

public class Problem4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			int n = sc.nextInt();
			int[] a = new int[n];
			for(int i = 0;i < n; i++) {
				a[i] = sc.nextInt();
			}
			solve(n, a);
			t--;
		}
	}
	
	public static void solve(int n, int[] a) {
		int AMove = 1, BMove = 0, ASum = a[0], BSum = 0;
		int ATotalSum = a[0], BTotalSum = 0;
		int i = 1,j = n - 1;
		while(i < j) {
			BSum = 0;
			boolean flag = false;
			while(a[j] != -1 && i <= j && BSum <= ASum) {
				flag = true;
				BSum += a[j];
				a[j] = -1;
				j--;
			}
			if(flag)
				BMove++;
			BTotalSum += BSum;
			ASum = 0;
			flag = false;
			while(a[i] != -1 && i  <= j && ASum <= BSum) {
				flag = true;
				ASum += a[i];
				a[i] = -1;
				i++;
			}
			i--;
			if(flag)
				AMove++;
			ATotalSum += ASum;
			
		}
		System.out.println((AMove + BMove) + " " + ATotalSum + " " + BTotalSum);
	}

}
