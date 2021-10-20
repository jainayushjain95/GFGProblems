package lc.google;

import java.util.*;

public class WordBreak {

	public static void main(String[] args) {
	
	}

	public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<String>();
        for(String word : wordDict) {
        	set.add(word);
        }
        
        return solve(s, 0, set);
    }
	
	public boolean solve(String s, int beginIndex, Set<String> set) {
		if(beginIndex >= s.length()) {
			return true;
		}
		
		for(int index = beginIndex + 1;index <= s.length(); index++) {
			if(set.contains(s.substring(beginIndex, index)) && solve(s, index, set)) {
				return true;
			}
		}
		return false;
	}
}
