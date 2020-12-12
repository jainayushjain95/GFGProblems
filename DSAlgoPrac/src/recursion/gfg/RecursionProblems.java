package recursion.gfg;

public class RecursionProblems {

	public static void main(String[] args) {
		String S = "abc";
		printSubsets(S, "", 0);
	}

	public static boolean isPalindrome(String S, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
			return true;
		}
		
		return S.charAt(beginIndex) == S.charAt(endIndex) && isPalindrome(S, beginIndex + 1, endIndex - 1);
	}
	
	public static int sumOfDigits(int n) {
		if(n < 10) {
			return n;
		}
		
		return (n % 10) + sumOfDigits(n/10);
	}
	
	public static int ropeCuttingProblem(int n, int a, int b, int c) {
		if(n == 0) {
			return 0;
		}
		if(n < 0) {
			return -1;
		}
		return 1 + Math.max(ropeCuttingProblem(n - a, a, b, c), Math.max(ropeCuttingProblem(n - b, a, b, c), ropeCuttingProblem(n - c, a, b, c)));
	}
	
	public static void printSubsets(String S, String curr, int index) {
		if(index == S.length()) {
			System.out.println(curr);
			return;
		}
		printSubsets(S, curr, index + 1);
		printSubsets(S, curr + S.charAt(index), index + 1);
	}
}
