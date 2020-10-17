package amazon;

public class StringFormationFromSubstring {

	public static void main(String[] args) {
		String S = "fptvmfptvmfptv";
		System.out.println(solve(S));
	}
	
	public static int solve(String S) {
		int N = S.length(), i = 1, j = 0;
		int[] lpsArray = new int[N];
		for(i = 1; i < N; i++) {
			if(S.charAt(i) == S.charAt(j)) {
				lpsArray[i] = ++j;
			} else {
				i = i - j;
				j = 0;
			}
		}
		int lps = lpsArray[N - 1];
		int ans = lps > 0 && (N % (N - lps) == 0) ? 1 : 0;
		return ans;
	}

}
