package amazon;

import java.util.Scanner;

public class DecodeIt {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//System.out.println((int)'0' - 48);
		int t = Integer.parseInt(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		while(t != 0) {
			sb.append(solve(sc.nextLine(), Integer.parseInt(sc.nextLine())));
			sb.append("\n");
			t--;
		}
		System.out.println(sb.toString());
	}

	public static boolean isCharNumeric(char c) {
		int digit = (int)(c) - 48;
		return digit >= 0 && digit <= 9;
	}
	
	public static int getIntFromChar(char c) {
		return (int)c - 48;
	}
	
	public static String solve(String S, int K) {
		String solution = "";
		int N = S.length();
		if(K == 1) {
			solution = "" + S.charAt(0);
		} else {
			K--;
			int index = -1, left = 0, right = 0, prev = 0;
			
			while(left < N && right < N) {
				if(K == 0) {
					if(!isCharNumeric(S.charAt(right))) {
						solution = "" + S.charAt(right);	
					} else {
						solution = "" + S.charAt(right - 1);
					}
					break;
				}
				if(!isCharNumeric(S.charAt(right))) {
					right++;
					K--;
				} else {
					int quantum = getIntFromChar(S.charAt(right)) - 1;
					int width = right - left;
					int newK = K - quantum * width;
					if(newK > 0) {
						K = newK;
					} else {
						solution = "" + S.charAt((left + K)%(right - left));
						break;
					}
					left = right + 1;
					right = left;
				}
			}
		}
		
		return solution;
	}
}
