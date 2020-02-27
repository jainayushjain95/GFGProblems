package recursion;

public class Problem16 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isPalindrome("namanq"));
	}

	public static boolean isPalindrome(String input) {
		if(input.length() <= 1)
			return true;
		return (input.charAt(0) == input.charAt(input.length()-1)) && isPalindrome(input.substring(1, input.length()-1));
	}
	
}
