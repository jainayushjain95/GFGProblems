package strings.lc;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {

	public static void main(String[] args) {
		String s = "a";
		System.out.println(lengthOfLongestSubstringSolve(s));
	}

	public static int lengthOfLongestSubstringSolve(String s) {
		int length = s.length();
		int maxLength = 0;
		int i = 0, j = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		while(i < length && j < length) {
			int currLength = 0;
			while(j < length) {
				if(!map.containsKey(s.charAt(j)) || map.get(s.charAt(j)) < i) {
					currLength++;
					maxLength = Math.max(maxLength, currLength);
				} else {
					i = map.get(s.charAt(j)) + 1;
					currLength = j - i + 1;
				}
				map.put(s.charAt(j), j);
				j++;
			}
		}
		
		return maxLength;
	}

}
