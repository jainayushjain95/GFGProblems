package may87;

import java.util.Scanner;

public class Problem1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			long a = sc.nextLong();
			long b = sc.nextLong();
			long c = sc.nextLong();
			long d = sc.nextLong();
			str.append(solve(a, b, c, d) + "\n");
			t--;
		}
		System.out.print(str.toString());
	}

	public static String solve(long a, long b, long c, long d) {
		StringBuilder str = new StringBuilder();
		long sum = 0;
		if((a - b) <= 0) {
			str.append(b + "");
		} else if(a > 0 && c <= d) {
			str.append("-1");
		} else {
			a = a - b;
			sum += b;
			long x = a / (c - d);
			if(a % (c - d) != 0) {
				x++;
			}
			sum += c * x;
			str.append(sum + "");
		}
		return str.toString();
	}
	
}
