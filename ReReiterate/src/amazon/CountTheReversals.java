package amazon;

import java.util.Scanner;
import java.util.Stack;

public class CountTheReversals {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(sc.nextLine());
		while(t != 0) {
			t--;
			sb.append(solve(sc.nextLine()) + "\n");
		}
		System.out.println(sb.toString());
	}
	
	public static int solve(String S) {
		int ans = 0, N = S.length();
		if(N % 2 != 0) {
			ans = -1;
		} else {
			Stack<Character> st = new Stack<Character>();
			for(int i = 0;i < N; i++) {
				char c = S.charAt(i);
				if(c == '{') { 
					st.push(c);
				} else if(st.isEmpty() || st.peek() == '}'){
					st.push(c);
				} else {
					st.pop();
				}
			}
			int oCount = 0, closeCount = 0;
			while(!st.isEmpty()) {
				char c = st.pop();
				if(c == '{') {
					oCount++;
				} else {
					closeCount++;
				}
			}
			ans = (int)Math.ceil((double)oCount/2) + (int)Math.ceil((double)closeCount/2);
		}
		return ans;
	}

}
