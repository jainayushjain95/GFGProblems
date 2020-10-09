package strings;

public class SmallestWindowInStringContainingAllTheCharactersOfAnotherString {

	public static void main(String[] args) {
		String S = "aaat", P = "t";
		System.out.println(solve(S, P));
	}

	public static String solve(String S, String P) {
		String solution = "";
		if(S.length() < P.length()) {
			solution = "-1";
		} else if(S.length() == P.length()){
			if(S.equals(P)) {
				solution = S;
			} else {
				solution = "-1";
			}
		} else {
			int left = 0, right = 0;
			int bestLeft = -1, bestRight = -1;
			int bestLength = Integer.MAX_VALUE;

			int[] alphaPattern = new int[255];

			for(int i = 0; i < P.length(); i++) {
				alphaPattern[P.charAt(i)]++;
			}

			int[] alphaText = new int[255];
			
			alphaText[S.charAt(0)]++;
			while(left < S.length() && right < S.length()) {
				System.out.println(left + " : " + right);
				if(isSatisfies(alphaPattern, alphaText)) {
					int currentLength = right - left + 1;
					if(currentLength < bestLength) {
						bestLeft = left;
						bestRight = right;
					}
					bestLength = Math.min(bestLength, currentLength);
					left++;
					alphaText[S.charAt(left - 1)]--;
				} else {
					right++;
					if(right < S.length()) {
						alphaText[S.charAt(right)]++;	
					}
				}
			}
			solution = (bestLength == Integer.MAX_VALUE) ? "-1" : S.substring(bestLeft, bestRight + 1);
		}
		return solution;
	}
	

	public static boolean isSatisfies(int[] alphaPattern, int[] alphaText) {
		return check(alphaText, alphaPattern);
	}

	public static boolean check(int[] alphaText, int[] alphaPattern) {
		boolean satisfies = true;
		for(int i = 0;i < 255; i++) {
			if(alphaPattern[i] > alphaText[i]) {
				satisfies = false;
				break;
			}
		}
		return satisfies;
	}

}
