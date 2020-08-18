package strings.geeksforgeeks;

public class LexicographicRankOfAString {
	public static void main(String[] args) {
		String input = "acddeeaghfaee";
		lexicographicRankOfAString(input);
	}
	
	public static void lexicographicRankOfAString(String input) {
		int[] p = new int[256];
		int n = input.length();
		for(int i = 0;i < n; i++) {
			p[input.charAt(i)]++;
		}
		
		for(int i = 1;i < 256; i++) {
			p[i] += p[i - 1];
		}
		int rank = 1;
		int fact = fact(n);
		
		for(int i = 0;i < n; i++) {
			fact = fact / (n - i);
			char curr = input.charAt(i);
			int noOfCharactersStrictlySmallerThanCurr = p[curr - 1];
			rank += fact * noOfCharactersStrictlySmallerThanCurr;
			for(int temp = curr; temp < 256; temp++) {
				p[temp]--;
			}
		}
		System.out.println(rank);
	}
	
	public static int fact(int n) {
		int fact = 1;
		while(n > 1) {
			fact *= n--;
		}
		return fact;
	}
}
