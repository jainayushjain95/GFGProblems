package lc.strings;

public class Problems {

	public static void main(String[] args) {
		String str1 = "ABCABC", str2 = "ABC";
		System.out.println((new Problems()).gcdOfStrings(str1, str2));
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
