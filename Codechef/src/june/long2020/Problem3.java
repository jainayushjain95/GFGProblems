package june.long2020;

import java.util.Scanner;

public class Problem3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			int n = sc.nextInt();
			int i = 0, countOf5Coins = 0, countOf10Coins = 0;
			
			boolean flag = true;
			
			while(i < n) {
				int coin = sc.nextInt();
				if(coin == 5) {
					countOf5Coins++;
				} else if(coin == 10) {
					if(countOf5Coins != 0) {
						countOf5Coins--;
						countOf10Coins++;	
					} else {
						flag = false;
					}
				} else {
					if(countOf10Coins != 0) {
						countOf10Coins--;
					} else if(countOf5Coins > 1) {
						countOf5Coins -= 2;
					} else {
						flag = false;
					}
				}
				i++;
			}
			
			if(!flag) {
				str.append("NO\n");
			} else {
				str.append("YES\n");
			}
			
			t--;
		}
		System.out.println(str.toString());
	}

}
