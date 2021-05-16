package lc.strings;

public class MinimumWIndowSubstring {

	public static void main(String[] args) {
		String s = "ADOBECODEBANC", t = "ABC";
		System.out.println(minWindowSolve(s, t));
	}

	public static String minWindowSolve(String s, String t) {
		String solution = "";
		int m = s.length(), n = t.length();

		int[] tAlpha = getAlphaArray(t);
		int[] sAlpha = new int[256];
		int left = -1, right = -1;
		int minLen = Integer.MAX_VALUE;
		
		if(m >= n) {
			sAlpha[s.charAt(0)]++;
			int count = 0;
			if(sAlpha[s.charAt(0)] >= tAlpha[s.charAt(0)]) {
				count++;
			}
			int i = 0, j = 0;
			boolean isContains = count == n || isContains(sAlpha, tAlpha);
			while(i < m && j < m) {
				if(isContains) {
					int currentLength = j - i + 1;
					if(currentLength < minLen) {
						minLen = currentLength;
						left = i;
						right = j;
						if(minLen == n) {
							break;
						}
					}
					i++;
					sAlpha[s.charAt(i - 1)]--;
					isContains = !(sAlpha[s.charAt(i - 1)] < tAlpha[s.charAt(i - 1)]);
					if(!isContains) {
						count--;
					}
				} else {
					j++;
					if(j < m) {
						sAlpha[s.charAt(j)]++;
						if(sAlpha[s.charAt(j)] >= tAlpha[s.charAt(j)]) {
							count++;
						}
					}
					isContains = count == n;//isContains(sAlpha, tAlpha);
				}
			}
			solution = (minLen == Integer.MAX_VALUE) ? "" : s.substring(left, right + 1);
		}

		return solution;
	}


	public static boolean isContains(int[] sAlpha, int[] tAlpha) {
		boolean isContains = true;

		for(int i = 0;i < sAlpha.length; i++) {
			if(sAlpha[i] < tAlpha[i]) {
				isContains = false;
				break;
			}
		}

		return isContains;
	}

	public static int[] getAlphaArray(String input) {
		int[] alpha = new int[256];
		for(int i = 0;i < input.length(); i++) {
			alpha[input.charAt(i)]++;
		}
		return alpha;
	}

}
