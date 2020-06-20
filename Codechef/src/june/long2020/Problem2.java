package june.long2020;

import java.util.Scanner;

public class Problem2 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			str.append(solve(sc.next()) + "\n");
			t--;
		}
		System.out.println(str.toString());
	}

	public static String solve(String input) {
		StringBuilder str = new StringBuilder();
		int maxPossiblePairs = 0;
		for(int i = 0;i < input.length() - 1;) {
			if(input.charAt(i) != input.charAt(i + 1)) {
				maxPossiblePairs++;
				i = i + 2;
			} else {
				i++;
			}
		}
		str.append(maxPossiblePairs);
		return str.toString();
	}
}
