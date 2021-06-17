package lc.strings;

public class Problems {

	public static void main(String[] args) {
		String str1 = "ABCABC", str2 = "ABC";
		System.out.println((new Problems()).reorganizeString("bfrbs"));
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
