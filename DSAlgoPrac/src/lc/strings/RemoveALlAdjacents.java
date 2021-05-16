package lc.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

class CharIntPair {
	char c;
	int freq;
	public CharIntPair(char c, int freq) {
		super();
		this.c = c;
		this.freq = freq;
	}

}

public class RemoveALlAdjacents {

	public static void main(String[] args) {
		String s = "pbbcggttciiippooaais";
		int k = 2;
		System.out.println(removeDups(s, k));
	}

	public static String removeDups(String s, int k) {
		String solution = "";
		StringBuilder stringBuilder = new StringBuilder();
		Stack<CharIntPair> stack = new Stack<CharIntPair>();
		if(s.length() <= k) {
			solution = s;
		} else {
			for(int i = 0;i < s.length(); i++) {
				char c = s.charAt(i);
				if(stack.isEmpty()) {
					stack.push(new CharIntPair(c, 1));
				} else if(stack.peek().c == c && stack.peek().freq == k - 1) {
					stack.pop();
				} else if(stack.peek().c == c) {
					CharIntPair top = stack.pop();
					stack.push(new CharIntPair(c, top.freq + 1));
				} else {
					stack.push(new CharIntPair(c, 1));
				}	
			}
			while(!stack.isEmpty()) {
				CharIntPair charIntPair = stack.pop(); 
				int freq = charIntPair.freq;
				while(freq != 0) {
					stringBuilder.append(charIntPair.c);
					freq--;
				}
			}
			solution = stringBuilder.reverse().toString();
		}

		return solution;
	}

	public static String removeDuplicatesDriver(String s, int k) {
		String solution = s;
		String input = s;
		do {
			input = solution;
			solution = removeDuplicatesSolve(input, k);
		} while(!solution.equals(input));

		return solution;
	}

	public static String removeDuplicatesSolve(String s, int k) {
		String solve = "";
		if(s.length() <= k) {
			solve = s;
		} else {
			boolean isMapRenewed = false;
			char[] chars = getCharsArray(s);
			Map<Character, Integer> map = new HashMap<Character, Integer>();
			for(int i = 0;i <= (chars.length - k);) {
				if(i == 0) {
					map = getMap(chars, 0, k);
				} else if(isMapRenewed) {
					map = getMap(chars, i, k);
					isMapRenewed = false;
				} else {
					if(map.get(chars[i - 1]) == 1) {
						map.remove(chars[i - 1]);
					} else {
						map.put(chars[i - 1], map.get(chars[i - 1]) - 1); 
					}

					if(!map.containsKey(chars[i + k - 1])) {
						map.put(chars[i + k - 1], 1);
					} else {
						map.put(chars[i + k - 1], 1 + map.get(chars[i + k - 1]));
					}
				}
				if(map.size() == 1) {
					chars[i] = '-';
					i = i + k;
					isMapRenewed = true;
				} else {
					i++;
				}
			}

			StringBuilder stringBuilder = new StringBuilder();
			for(int i = 0;i < chars.length; i++) {
				if(chars[i] != '-') {
					stringBuilder.append(chars[i]);
				} else {
					i = i + k - 1;
				}
			}
			solve = stringBuilder.toString();
		}

		return solve;
	}

	public static Map<Character, Integer> getMap(char[] chars, int beginIndex, int count) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		while(count != 0) {
			if(!map.containsKey(chars[beginIndex])) {
				map.put(chars[beginIndex], 1);
			} else {
				map.put(chars[beginIndex], map.get(chars[beginIndex]) + 1);
			}
			beginIndex++;
			count--;
		}
		return map;
	}

	public static char[] getCharsArray(String s) {
		char[] chars = new char[s.length()];
		for(int i = 0;i < s.length(); i++) {
			chars[i] = s.charAt(i);
		}
		return chars;
	}

}
