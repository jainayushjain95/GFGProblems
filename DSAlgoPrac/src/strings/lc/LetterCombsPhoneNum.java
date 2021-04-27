package strings.lc;

import java.util.ArrayList;
import java.util.List;

public class LetterCombsPhoneNum {

	public static void main(String[] args) {
		String digits = "23";
		List<String> data = letterCombinationsSolve(digits, 0);
		for(String a : data) {
			System.out.println(a);
		}
	}

	public static List<String> letterCombinationsSolve(String digits, int beginIndex) {
		
		if(beginIndex > digits.length() - 1) {
			return new ArrayList<String>();
		}
		
		if(beginIndex == digits.length() - 1) {
			List<String> data = new ArrayList<String>();
			String word = getStringEquivalent(digits.charAt(beginIndex));
			for(int i = 0;i < word.length(); i++) {
				data.add(word.charAt(i) + "");
			}
			return data;
		}
		
		List<String> data = letterCombinationsSolve(digits, beginIndex + 1);
		String word = getStringEquivalent(digits.charAt(beginIndex));
		
		List<String> solve = new ArrayList<String>();
		
		for(int i = 0;i < word.length(); i++) {
			char c = word.charAt(i);
			for(int j = 0;j < data.size(); j++) {
				solve.add(c + data.get(j));
			}
		}
		
		return solve;
	}

	public static String getStringEquivalent(char n) {
		String word = "";
		switch(n) {
		case '2': 
			word = "abc";
			break;
		case '3': 
			word = "def";
			break;
		case '4': 
			word = "ghi";
			break;
		case '5': 
			word = "jkl";
			break;
		case '6': 
			word = "mno";
			break;
		case '7': 
			word = "pqrs";
			break;
		case '8': 
			word = "tuv";
			break;
		case '9': 
			word = "wxyz";
			break;
		}
		return word;
	}

}
