package may640;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SumofRoundNumbers {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			int n = sc.nextInt();
			solve(n);
			t--;
		}
	}
	
	public static void solve(int n) {
		int nod , temp = n;
		List<Integer> list = new ArrayList<Integer>();
		nod = getNoOfDigits(n);
		for(int i = 1;i <= nod; i++) {
			int mod = n % (int)(Math.pow(10, i));
			if(mod != 0) {
				list.add(mod);
				n = n - mod;
			}
		}
		System.out.println(list.size());
		for(int i = 0;i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}
	
	public static int getNoOfDigits(int n) {
		return 1 + (int)Math.floor(Math.log10(n));
	}
}
