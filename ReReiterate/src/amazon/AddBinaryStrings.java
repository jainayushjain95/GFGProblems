package amazon;

public class AddBinaryStrings {

	public static void main(String[] args) {
		String A = "111", B = "1101";
//		System.out.println((int)('0') - 48);
		System.out.println(solve(A, B));
	}
	
	public static String removeLeadingZeroes(String A) {
		if(A.length() > 1) {
			if(A.contains("1")) {
				int index = A.indexOf('1');
				A = A.substring(index);
			} else {
				A = "0";
			}
		}
		return A;
	}
	
	public static String solve(String A, String B) {
		A = removeLeadingZeroes(A);
		B = removeLeadingZeroes(B);
		String solution = "";
		StringBuilder sb = new StringBuilder();

		int m = A.length(), n = B.length(), carry = 0;
		
		int i = m - 1, j = n - 1;
		while(i >= 0 || j >= 0) {
			int a = (i >= 0) ? getIntFromChar(A.charAt(i)) : 0;
			int b = (j >= 0) ? getIntFromChar(B.charAt(j)) : 0;
			int totalSum = a + b + carry;
			int sum = 0;
			if(totalSum == 0) {
				sum = 0;
				carry = 0;
			} else if(totalSum == 1) {
				sum = 1;
				carry = 0;
			} else if(totalSum == 2) {
				sum = 0;
				carry = 1;
			} else {
				sum = 1;
				carry = 1;
			}
			i--;
			j--;
			sb.append(sum);
		}
		if(carry != 0) {
			sb.append(carry);
		}
		solution = sb.reverse().toString();
		return solution;
	}
	
	public static int getIntFromChar(char c) {
		return (int)c - 48;
	}

}
