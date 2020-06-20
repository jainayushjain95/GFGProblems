package june.long2020;

import java.util.Scanner;

public class Problem1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
//		StringBuilder str = new StringBuilder();
		while(t != 0) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			int loss = 0;
			for(int i = 0;i < n; i++) {
				int p = sc.nextInt();
				if(p > k) {
					loss += p - k;
				}
			}
			System.out.println(loss);
//			str.append(loss + "\n");
			t--;
		}
//		System.out.println(str.toString());
	}
}
