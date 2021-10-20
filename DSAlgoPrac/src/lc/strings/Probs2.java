package lc.strings;

import java.util.*;

public class Probs2 {

	Map<Character, Integer> charMap;

	
	
	public static void main(String[] args) {
		String[] words = {"word","world", "row"};
		String order = "worldabcefghijkmnpqstuvxyz";
		System.out.println(new Probs2().isAlienSorted(words, order));
	}

	public boolean isAlienSorted(String[] words, String order) {
		charMap = getCharMap(order);
		for(int i = 0;i < words.length - 1; i++) {
			for(int j = 0;j < words[i].length(); j++) {
				if(j >= words[i + 1].length()) {
					return false;
				}
				char a = words[i].charAt(j);
				char b = words[i + 1].charAt(j);
				if(a != b) {
					if(charMap.get(a) > charMap.get(b)) {
						return false;
					} else {
						break;	
					}
				}
			}
		}
		return true;
	}


	public Map<Character, Integer> getCharMap(String order) {
		Map<Character, Integer> charMap = new HashMap<>();

		for(int i = 0;i < order.length(); i++) {
			charMap.put(order.charAt(i), i);    
		}

		return charMap;
	}

}
