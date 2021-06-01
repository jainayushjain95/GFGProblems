package lc.strings;

import java.util.ArrayList;
import java.util.List;

public class Probs {

	public static void main(String[] args) {
		System.out.println(strStr("a", "a"));
	}
	
	public static int strStr(String haystack, String needle) {
		int index = -1;
		if(needle.length() > 0) {
			int noOfWindows = 1 + haystack.length() - needle.length();
			for(int i = 0;i < noOfWindows; i++) {
				int j = 0;
				while(j < needle.length() && needle.charAt(j) == haystack.charAt(i + j)) {
					j++;
				}
				if(j == needle.length()) {
					index = i;
					break;
				}
			}
		} else {
			index = 0;
		}
		return index;
    }

	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> groupedAnagrams = new ArrayList<List<String>>();
		return groupedAnagrams;
	}
}
