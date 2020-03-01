package recursion;

import java.util.*;

public class Problem32 {

	public static HashMap<Integer, String> keyWordMap = new HashMap<Integer, String>();
	
	public static void main(String[] args) {
		getKeywordMap();
		int[] input = new int[5];
		input[0] = 2;
		input[1] = 3;
		input[2] = 4;
		input[3] = 5;
		input[4] = 6;
		ArrayList<String> output = getPossibleWords(input, 0, new ArrayList<String>());
		for(String x : output) {
			System.out.println(x);
		}
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

	public static ArrayList<String> getPossibleWords(int[] input, int index, ArrayList<String> current) {
		if((index + 1) == input.length) {
			ArrayList<String> smallerOutputTemp = new ArrayList<String>();
			String currentKeyStringTemp = keyWordMap.get(input[index]);
			for(int i = 0;i < currentKeyStringTemp.length(); i++) {
				smallerOutputTemp.add(currentKeyStringTemp.charAt(i) + "");
			}
			return smallerOutputTemp;
		}
		ArrayList<String> smallerOutput = getPossibleWords(input, index + 1, current);
		String currentKeyString = keyWordMap.get(input[index]);
		
		ArrayList<String> smallerOutputTemp = new ArrayList<String>();
		
		for(int i = 0;i < currentKeyString.length(); i++) {
			char x = currentKeyString.charAt(i);
			for(String y : smallerOutput) {
				smallerOutputTemp.add(x + y);
			}
		}
		smallerOutput = smallerOutputTemp;
		return smallerOutput;
	}
	
}
