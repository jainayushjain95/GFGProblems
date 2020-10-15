package amazon;

public class RunLengthEncoding {

	public static void main(String[] args) {
		System.out.println(solve("a"));
	}
	
	public static String solve(String S) {
		String solution = "";
		int left = 0, right = 0, n = S.length();
		
		while(left < n && right <= n) {
			if(right == n) {
				solution = solution + S.charAt(left) + (right - left);
				right++;
			} else	if(S.charAt(left) == S.charAt(right)) {
				right++;
			} else {
				solution = solution + S.charAt(left) + (right - left);
				left = right;
			}
		}
		
		return solution;
	}

}
