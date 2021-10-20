package cf.sep18;

import java.util.*;

public class Prob1 {

	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int t = sc.nextInt();
		StringBuilder stringBuilder = new StringBuilder();
		while(t != 0) {
			int n = sc.nextInt();
			String input = sc.next();
			stringBuilder.append(solve(input));
			stringBuilder.append("\n");
			t--;
		}
		System.out.println(stringBuilder.toString());
	}
	
	public static int solve(String input) {
		int count = 0;
		for(int i = 0;i < input.length(); i++) {
			int element = input.charAt(i) - '0';
			count += element;
			if(element != 0 && i < input.length() - 1) {
				count++;
			}
		}
		return count;
	}

}
