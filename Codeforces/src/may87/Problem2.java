package may87;

import java.util.Scanner;

public class Problem2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			String s = sc.next();
			str.append(solve(s) + "\n");
			t--;
		}
		System.out.println(str.toString());
	}

	public static String solve(String s) {
		StringBuilder str = new StringBuilder();
		if(s.length() < 3) {
			str.append(0);
		} else {
			boolean one = false, two = false, three = false;
			int i = 0;
			int o = -1, tw = -1, th = -1; 
			int minL = Integer.MAX_VALUE;
			while(i < s.length()) {
				if(s.charAt(i) == '1') {
					one = true;
					o = i;
				}
				
				if(s.charAt(i) == '2') {
					two = true;
					tw = i;
				}
				
				if(s.charAt(i) == '3') {
					three = true;
					th = i;
				}
				i++;
				if(one && two && three) {
					int min = Math.min(o, Math.min(tw, th));
					int max = Math.max(o, Math.max(tw, th));
					minL = Math.min(minL, max - min + 1);
				}
			}
			if(minL == Integer.MAX_VALUE) {
				str.append(0);
			} else {
				str.append(minL);
			}
		}
		return str.toString();
	}
}
