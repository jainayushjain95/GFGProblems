package lc.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RankTeams {

	public static int wordLength;
	
	public static void main(String[] args) {
		String[] votes = {"BCA","CAB","CBA","ABC","ACB","BAC"};
		(new RankTeams()).rankTeams(votes);
	}

	public String rankTeams(String[] votes) {
		wordLength = votes[0].length();
		Character[] chars = new Character[wordLength];
		int index = 0;
		Map<Character, Integer[]> map = new HashMap<Character, Integer[]>();
		
		for(int i = 0;i < votes.length; i++) {
			String word = votes[i];
			for(int j = 0;j < wordLength; j++) {
				Character c = word.charAt(j);
				if(i == 0) {
					chars[index++] = c;
				}
				if(map.containsKey(c)) {
					map.get(c)[j]++;
				} else {
					Integer[] charArray = new Integer[wordLength];
					for(int k = 0;k < wordLength; k++) {
						charArray[k] = 0;
					}
					charArray[j]++;
					map.put(c, charArray);
				}
			}
		}
		
		Arrays.sort(chars, (charA, charB) -> {
			int i = 0 ;
			Integer[] list1 = map.get(charA);
			Integer[] list2 = map.get(charB);
			while(i < list1.length && list1[i] == list2[i]) {
				i++;
			}
			if(i == list1.length) {
				return charA - charB;
			} else {
				return -list1[i] + list2[i];
			}
		});
		
		StringBuilder stringBuilder = new StringBuilder();
		for(Character c : chars) {
			stringBuilder.append(c);
		}
		
		return stringBuilder.toString();
	}
}
