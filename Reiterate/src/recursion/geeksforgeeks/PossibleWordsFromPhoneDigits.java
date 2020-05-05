package recursion.geeksforgeeks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PossibleWordsFromPhoneDigits {

	public static HashMap<Integer, String> keyWordMap = new HashMap<Integer, String>();
	
	public static void main(String[] args) {
		int n = 234244;
		List<String> words = new ArrayList<String>();
		getKeywordMap();
		words = possibleWords(n, words);
		
		for(String x : words) {
			System.out.println(x);
		}
	}
	
	public static List<String> possibleWords(int n, List<String> words) {
		if(n <= 1) {
			return words;
		}
		int mod = n % 10;
		String keypad = keyWordMap.get(mod);
		
		if(words.size() == 0) {
			for(int i = 0; i < keypad.length(); i++) {
				words.add("" + keypad.charAt(i));
			}
		} else {
			List<String> updatedWordsList = new ArrayList<String>();
			for(int i = 0; i < keypad.length(); i++) {
				for(String x : words) {
					updatedWordsList.add(keypad.charAt(i) + x);
				}
			}
			words = updatedWordsList;
		}
		n = n / 10;
		return possibleWords(n, words);
	}
	
	public static void getKeywordMap() {
		keyWordMap.put(2, "abc");
		keyWordMap.put(3, "def");
		keyWordMap.put(4, "ghi");
		keyWordMap.put(5, "jkl");
		keyWordMap.put(6, "mno");
		keyWordMap.put(7, "pqrs");
		keyWordMap.put(8, "tuv");
		keyWordMap.put(9, "wxyz");
	}

}
