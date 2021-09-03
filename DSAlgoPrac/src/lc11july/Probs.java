package lc11july;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Probs {
	
	Set<String> set;
	boolean[][] memo;
	
//	static int count = 0;
	
	
	public static void main(String[] args) {
		System.out.println((new Probs().countPalindromicSubsequence("bbcbaba")));
	}
	
	public int countPalindromicSubsequence(String s) {
		set = new HashSet<String>();
		memo = new boolean[s.length()][s.length()];
		countPalindromicSubsequenceSolve(s);
		return set.size();
    }
	
	
	public void countPalindromicSubsequenceSolve(String s) {
		int beginIndex = 0, endIndex = 0;
		
		while(beginIndex < s.length() - 1) {
			endIndex = beginIndex + 2;
			while(endIndex < s.length()) {
				if(!memo[beginIndex][endIndex]) {
					if(s.charAt(beginIndex) == s.charAt(endIndex)) {
						checkPalindromes(s, beginIndex, endIndex);	
					}	
				}
				memo[beginIndex][endIndex] = true;
				endIndex++;
			}
			beginIndex++;
		}
	}
	
	public void checkPalindromes(String s, int beginIndex, int endIndex) {
		String end = s.charAt(endIndex) + "";
		int index = beginIndex + 1;
		
		while(index < endIndex) {
			StringBuilder sb = new StringBuilder(s.charAt(beginIndex));
			sb.append(s.charAt(index));
			sb.append(s.charAt(endIndex));
			
			String palindrome = sb.toString();
			if(!set.contains(palindrome)) {
				set.add(palindrome);
			}
			index++;
		}
	}

}
