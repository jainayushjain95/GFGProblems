package amazon;

public class LongestPrefixSuffix {

	public static void main(String[] args) {
		String S = "yycgicgyyycg";
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
		return lpsArray[N - 1];
	}

}
