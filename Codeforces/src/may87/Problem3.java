package may87;

import java.util.Scanner;

public class Problem3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			str.append(solve(sc.nextDouble()) + "\n");
			t--;
		}
		System.out.println(str.toString());
	}

	public static String solve(double n) {
		StringBuilder str = new StringBuilder();
		double length = 2.0 * n;
		double angle = (Math.PI) / length;
		double output = 1/ Math.tan(angle);
		str.append(output);
		return str.toString();
	}
}
