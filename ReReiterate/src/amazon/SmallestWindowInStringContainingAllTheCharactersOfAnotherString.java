package amazon;

public class SmallestWindowInStringContainingAllTheCharactersOfAnotherString {

	public static void main(String[] args) {
		String S = "aaat", P = "at";
		System.out.println(solve(S, P));
	}

	public static String solve(String S, String P) {
		String solution = "";
		int m = S.length(), n = P.length();
		
		if(m < n) {
			solution = "-1";
		} else if(m == n) {
			if(S.equals(P)) {
				solution = S;
			} else {
				solution = "-1";
			}
		} else {
			int bestLeft = -1, bestRight = -1, left = 0, right = 0, bestLength = Integer.MAX_VALUE;
			int[] alphaText = new int[26];
			int[] alphaPattern = new int[26];
			
			for(int i = 0;i < n; i++) {
				alphaPattern[P.charAt(i) - 97]++;
			}
			
			alphaText[S.charAt(0) - 97]++;
			while(left < m && right < m) {
				if(isSatisfies(alphaText, alphaPattern)) {
					int length = right - left + 1;
					if(bestLength > length) {
						bestLeft = left;
						bestRight = right;
						bestLength = length;
					}
					alphaText[S.charAt(left) - 97]--;
					left++;
				} else {
					right++;
					if(right < m) {
						alphaText[S.charAt(right) - 97]++;
					}
				}
			}
			solution = (bestLength == Integer.MAX_VALUE) ? "-1" : S.substring(bestLeft, 1 + bestRight);
		}
		return solution;	
	}
	
	public static boolean isSatisfies(int[] alphaText, int[] alphaPattern) {
		boolean flag = true;
		for(int i = 0; i < 26; i++) {
			if(alphaPattern[i] > alphaText[i]) {
				flag = false;
				break;
			}
		}
		return flag;
	}
}
