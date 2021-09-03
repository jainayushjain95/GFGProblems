package lc.strings;

public class Problems {

	public static void main(String[] args) {
		String str1 = "ABCABC", str2 = "ABC";
		System.out.println((new Problems()).multiply("9", "99"));
	}

	public String multiply(String num1, String num2) {
		if(num1.equals("0") || num2.equals("0")) {
			return "0";
		}
		StringBuilder sol = new StringBuilder();
		
		int[] result = new int[num1.length() + num2.length()];
		int offset = 0, prod = 0, carry = 0, i = 0, j = 0, k = 0;
		
		for(i = num2.length() - 1; i >= 0; i--) {
			k = result.length - 1 - offset++;
			for(j = num1.length() - 1; j >= 0; j--) {
				prod = carry + result[k] + getInt(num2.charAt(i)) * getInt(num1.charAt(j));
				result[k] = prod % 10;
				carry = prod / 10;
				k--;
			}
			if(carry != 0) {
				result[k] += carry;
				carry = 0;
			}
		}
		
		int index = 0;
		while(index < result.length && result[index] == 0) {
			index++;
		}
		
		while(index < result.length) {
			sol.append(result[index]);
			index++;
		}
		
		return sol.toString();
	}

	public static int getInt(char c) {
		return c - '0';
	}

	public String reorganizeString(String S) {
		StringBuilder sol = new StringBuilder();
		int[] alpha = new int[26];
		int maxIndex = 0;

		for(int i = 0;i < S.length(); i++) {
			int index = S.charAt(i) - 97;
			alpha[index]++;
			if(alpha[index] > alpha[maxIndex]) {
				maxIndex = index; 
			}
		}		

		if(alpha[maxIndex] <= (Math.ceil(S.length() / 2.0))) {
			char[] chars = new char[S.length()];
			int index = 0, freq = alpha[maxIndex];
			char c = (char)(maxIndex + 97);
			while(freq != 0) {
				chars[index] = c;
				freq--;
				index = index + 2;
			}
			index = 1;
			for(int i = 0;i < 26; i++) {
				if(i == maxIndex) {
					continue;
				}
				freq = alpha[i];
				c = (char)(i + 97);
				while(freq != 0) {
					chars[index] = c;
					freq--;
					index = index + 2;
				}
			}

			for(int i = 0;i < chars.length; i++) {
				sol.append(chars[i]);
			}
		}


		return sol.toString();
	}

	public String gcdOfStrings(String str1, String str2) {
		if(!(str1 + str2).equals(str2 + str1)) {
			return "";
		}
		return str1.substring(0, gcd(str1.length(), str2.length()));
	}

	public static int gcd(int a, int b) {
		if(b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
}
