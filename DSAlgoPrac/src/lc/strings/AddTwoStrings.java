package lc.strings;

public class AddTwoStrings {

	public static void main(String[] args) {
		String num1 = "1";
		String num2 = "9";
		System.out.println(addStrings(num1, num2));
	}
	
	public static String addStrings(String num1, String num2) {
		String solution = "";
		int i = num1.length() - 1, j = num2.length() - 1;
		int carry = 0;
		
		while(i >= 0 && j >= 0) {
			int sum = carry + getIntegerEquivalent(num1.charAt(i)) + getIntegerEquivalent(num2.charAt(j));
			carry = sum / 10;
			solution = (sum % 10) + solution;
			i--;
			j--;
		}
		
		while(i >= 0) {
			int sum = carry + getIntegerEquivalent(num1.charAt(i));
			carry = sum / 10;
			solution = (sum % 10) + solution;
			i--;
		}
		
		while(j >= 0) {
			int sum = carry + getIntegerEquivalent(num2.charAt(j));
			carry = sum / 10;
			solution = (sum % 10) + solution;
			j--;
		}
		
		if(carry > 0) {
			solution = carry + solution;
		}
		
		return solution;
	}
	
	public static int getIntegerEquivalent(char c) {
		return Integer.parseInt(c + "");
	}

}
