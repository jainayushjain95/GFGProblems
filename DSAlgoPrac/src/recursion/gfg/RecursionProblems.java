package recursion.gfg;

public class RecursionProblems {

	public static void main(String[] args) {
		String S = "geeks";
		System.out.println(isPalindrome(S, 0, S.length() - 1));
	}

	public static boolean isPalindrome(String S, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
			return true;
		}
		
		return S.charAt(beginIndex) == S.charAt(endIndex) && isPalindrome(S, beginIndex + 1, endIndex - 1);
	}
}
