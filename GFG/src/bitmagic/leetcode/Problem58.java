package bitmagic.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem58 {

	public static void main(String[] args) {
//		int a = (int)'Z';
//		System.out.println(a);
		System.out.println(letterCasePerms("abC"));
	}

	public static List<String> letterCasePerms(String input) {
		int possiblePermsCount = 1;
		for(int i = 0; i < input.length(); i++) {
			char a = input.charAt(i);
			if(isAlphabet(a)) {
				possiblePermsCount = possiblePermsCount << 1;
			}
		}
		List<String> outputStrings = new ArrayList<String>();
		for(int i = 0;i < possiblePermsCount; i++) {
			int temp = 1;
			String newPerm = "";
			for(int j = 0; j < input.length(); j++) {
				char a = input.charAt(j);
				if(isAlphabet(a)) {
					if(isKthBitSet(i , temp)) {
						newPerm += (input.charAt(j) + "").toLowerCase();
					} else {
						newPerm += (input.charAt(j) + "").toUpperCase();
					}
					temp++;
				} else {
					newPerm += input.charAt(j);
				}
			}
			outputStrings.add(newPerm);
		}
		return outputStrings;
	}

	public static boolean isAlphabet(char x) {
		int a = (int)x;
		return a >= 65 && x <= 122;
	}

	public static boolean isKthBitSet(int n, int k) {
		return (n & (1 << (k - 1))) != 0;
	}

}
