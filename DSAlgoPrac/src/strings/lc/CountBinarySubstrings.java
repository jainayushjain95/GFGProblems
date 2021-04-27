package strings.lc;

public class CountBinarySubstrings {

	public static void main(String[] args) {
		String s = "0";
		System.out.println(countBinarySubstringSolve(s));
	}

	public static int countBinarySubstringSolve(String s) {
		int solution = 0;
		char start = s.charAt(0);
		int startCount = 0, endCount = 0;
		int i = 0, n = s.length();
		
		while(i < n) {
			while(i < n && start == s.charAt(i)) {
				startCount++;
				i++;
			}
			int newIndex = i;
			while(i < n && start != s.charAt(i)) {
				endCount++;
				i++;
			}
			solution = solution + Math.min(startCount, endCount);
			if(newIndex < n) {
				start = s.charAt(newIndex);
				startCount = endCount;
				endCount = 0;	
			}
		}
		
		return solution;
	}
	
}
