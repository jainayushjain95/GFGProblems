package amazon;

import java.util.Stack;

public class ValidSubstring {

	public static void main(String[] args) {
		String S = "())((())";
		System.out.println(solve(S));
	}
	
	 public static int solve(String S) {
	        int solution = 0;
	        int start = 0, n = S.length();
	   
	        int openCount = 0, closeCount = 0;
	        
	        while(start < n) {
	        	char c = S.charAt(start);
	        	if(c == '(') {
	        		openCount++;
	        	} else {
	        		closeCount++;
	        	}
	        	if(openCount == closeCount) {
	        		solution = Math.max(2 * openCount, solution);
	        	} else if(closeCount > openCount) {
	        		closeCount = 0;
	        		openCount = 0;
	        	}
	        	start++;
	        }
	        
	        start = n - 1;
	        openCount = 0;
	        closeCount = 0;
	        
	        while(start >= 0) {
	        	char c = S.charAt(start);
	        	if(c == '(') {
	        		openCount++;
	        	} else {
	        		closeCount++;
	        	}
	        	if(openCount == closeCount) {
	        		solution = Math.max(2 * openCount, solution);
	        	} else if(closeCount < openCount) {
	        		closeCount = 0;
	        		openCount = 0;
	        	}
	        	start--;
	        }
	        
	        return solution;
	    }

}
