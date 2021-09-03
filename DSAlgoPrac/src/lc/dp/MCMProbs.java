package lc.dp;

public class MCMProbs {

	int[][] memo;
	boolean[][] memo2;
	int[] memo3;
	
	public static void main(String[] args) {
		System.out.println((new MCMProbs().minCut("efe")));
	}

	public int minCut(String s) {
		memo3 = new int[s.length()];
		 if(s.length() <= 1) {
	            return 0;
		 }
		setPalindromicValues(s);
		
		return minCut3(s);
	}
	
	public int minCut3(String s) {
		for(int i = 1;i < s.length(); i++) {
			if(isPalindrome(s, 0, i)) {
				memo3[i] = 0;
				continue;
			}
			int min = Integer.MAX_VALUE;	
			for(int j = i;j > 0; j--) {
				if(isPalindrome(s, j, i)) {
					int ans = memo3[j - 1];
					min = Math.min(min, ans);
				}
			}
			memo3[i] = 1 + min;
		}
		return memo3[s.length() - 1];
	}
	
	public int minCut2(String s, int endIndex) {
		if(endIndex <= 0 || isPalindrome(s, 0, endIndex)) {
			return 0;
		}
		
		if(memo3[endIndex] > -1) {
			return memo3[endIndex];
		}
		
		int min = s.length();
		
		for(int index = endIndex; index >= 0; index--) {
			if(isPalindrome(s, index, endIndex)) {
				int ans = 1 + minCut2(s, index - 1);
				if(ans < min) {
					min = ans; 
				}
			}
		}
		
		memo3[endIndex] = min;
		
		return min;
	}
	
	public void setPalindromicValues(String s) {
		memo2 = new boolean[s.length()][s.length()];
		for(int i = 0;i < s.length(); i++) {
			memo2[i][i] = true;
		}
		
		for(int i = s.length() - 1;i >= 0; i--) {
			for(int j = i + 1;j < s.length(); j++) {
				if(i + 1 == j) {
					memo2[i][j] = s.charAt(i) == s.charAt(j);
				} else {
					memo2[i][j] = s.charAt(i) == s.charAt(j) && memo2[i + 1][j - 1];
				}
			}
		}
	}

	public int minCutRecursiveSolve(String s, int i, int j) {
		if(i >= j) {
			return 0;
		}

		if(isPalindrome(s, i, j)) {
			return 0;
		}

		if(memo[i][j] > -1) {
			return memo[i][j];
		}
		
		int min = Integer.MAX_VALUE;

		for(int k = i;k < j; k++) {
			int temp = 1 + minCutRecursiveSolve(s, i, k) + minCutRecursiveSolve(s, k + 1, j);
			if(min > temp) {
				min = temp;
			}
		}

		memo[i][j] = min;
		
		return min;
	}

	public boolean isPalindrome(String s, int i, int j) {
		return memo2[i][j];
	}


}
