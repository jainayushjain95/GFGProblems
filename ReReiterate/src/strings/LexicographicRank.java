package strings;

public class LexicographicRank {

	static int mod = 1000003;
					
	
	public static void main(String[] args) {
		String s = "vsrtkjpre";
		System.out.println(lexicographicRank(s));
	}

	public static long lexicographicRank(String s) {
		long rank = 1;
		int n = s.length();
		int[] alpha = new int[256];
		setAlpha(alpha, s);
		
		
		long multiplier = fact(n);
		
		for(int i = 0;i < n - 1; i++) {
			int charsSmallerCount = alpha[s.charAt(i) - 1];
			multiplier = multiplier / (n - i);
			rank = rank + multiplier * charsSmallerCount;
			if(rank >= mod) {
				System.out.println(rank);
				rank = rank % mod;
			}
			updateAlpha(alpha, s, i);
		}
		
		
		return rank;
	}
	
	public static void updateAlpha(int[] alpha, String s, int i) {
		for(int j = s.charAt(i); j < 256; j++) {
			alpha[j]--;
		}
	}
	
	public static void setAlpha(int[] alpha, String s) {
		for(int i = 0;i < s.length(); i++) {
			alpha[s.charAt(i)]++;
		}
		
		for(int i = 1;i < 256; i++) {
			alpha[i] += alpha[i - 1];
		}
	}
	
	public static long fact(int n) {
		long fact = 1;
		while(n > 1) {
			fact *= n--;
		}
		return fact;
	}
	
}
