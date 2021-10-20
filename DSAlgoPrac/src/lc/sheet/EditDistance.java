package lc.sheet;

public class EditDistance {

	public static void main(String[] args) {
		System.out.println((new EditDistance().minDistance("intention", "execution")));

	}
	
	
	public int minDistance(String word1, String word2) {
		return minDistanceSolve1(word1, word2, word1.length(), word2.length());
    }
	
	public int minDistanceSolve1(String word1, String word2, int n1, int n2) {
        if(n1 == 0) {
        	return n2;
        }
        
        if(n2 == 0) {
        	return n1;
        }
        
        if(word1.charAt(n1 - 1) == word2.charAt(n2 - 1)) {
        	return minDistanceSolve1(word1, word2, n1 - 1, n2 - 1);
        }
        
        int ans = minDistanceSolve1(word1, word2, n1, n2 - 1);
        ans = Math.min(ans, minDistanceSolve1(word1, word2, n1 - 1, n2));
        ans = 1 + Math.min(ans, minDistanceSolve1(word1, word2, n1 - 1, n2 - 1));
        
		return ans;
    }

}
