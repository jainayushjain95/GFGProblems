package lc.google;

public class EditDistance {

	int[][] dp;
	
	public static void main(String[] args) {
		System.out.println((new EditDistance().minDistance("horse", "ros")));
	}
	
	public int minDistance(String word1, String word2) {
		dp = new int[word1.length() + 1][word2.length() + 1];
		return minDistanceBottomUp(word1, word2, word1.length(), word2.length());
    }

	public int minDistanceBottomUp(String word1, String word2, int len1, int len2) {
		for(int i = 0;i <= len1; i++) {
			dp[i][0] = i;
		}
		
		for(int i = 0;i <= len2; i++) {
			dp[0][i] = i;
		}
		
		for(int i = 1;i <= len1; i++) {
			for(int j = 1;j <= len2; j++) {
				if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
				}
			}
		}
		
		return dp[len1][len2];
	}
	
	public int minDistance(String word1, String word2, int len1, int len2) {
		
		if(len1 == 0) {
			dp[len1][len2] = len2;
			return len2;
		}
		
		if(len2 == 0) {
			dp[len1][len2] = len1;
			return len1;
		}
		
		if(dp[len1][len2] > 0) {
			return dp[len1][len2];
		}
		
		if(word1.charAt(len1 - 1) == word2.charAt(len2 - 1)) {
			dp[len1][len2] = minDistance(word1, word2, len1 - 1, len2 - 1);
			return dp[len1][len2];
		}
		
		int solution = Integer.MAX_VALUE;
		
		solution = Math.min(solution, minDistance(word1, word2, len1, len2 - 1));
		solution = Math.min(solution, minDistance(word1, word2, len1 - 1, len2));
		solution = Math.min(solution, minDistance(word1, word2, len1 - 1, len2 - 1));
		
		solution++;
		dp[len1][len2] = solution;
		return solution;
    }
}
