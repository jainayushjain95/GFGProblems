package lc.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class DataMap2 {
	Map<Character, List<Character>> adjaceny;
	Map<Character, Integer> indegrees;
	public DataMap2(Map<Character, List<Character>> adjaceny, Map<Character, Integer> indegrees) {
		super();
		this.adjaceny = adjaceny;
		this.indegrees = indegrees;
	}
}

public class AlientDictionary {

	public static void main(String[] args) {
		String[] words = {"bc", "b", "cbc"};
		System.out.println((new AlientDictionary().alienOrder(words)));
	}

	public String alienOrder(String[] words) {
		Set<Character> charSet = getCharSet(words);
		DataMap2 dataMap = getDataMap(words);
		Map<Character, List<Character>> adjaceny = dataMap.adjaceny;
		Map<Character, Integer> indegrees = dataMap.indegrees;
		if(indegrees == null || adjaceny == null) {
			return "";
		}
		Queue<Character> queue = prepareQueue(charSet, indegrees);
		StringBuilder stringBuilder = new StringBuilder();
		
		while(!queue.isEmpty()) {
			char c = queue.poll();
			stringBuilder.append(c);
			List<Character> adjacents = adjaceny.get(c);
			if(adjacents != null) {
				for(char currChar : adjacents) {
					indegrees.put(currChar, indegrees.get(currChar) - 1);
					if(indegrees.get(currChar) == 0) {
						queue.add(currChar);
					}
				}
			}
		}
		return stringBuilder.length() == charSet.size() ? stringBuilder.toString() : "";
    }
	
	public Queue<Character> prepareQueue(Set<Character> charSet, Map<Character, Integer> indegrees) {
		Queue<Character> queue = new LinkedList<Character>();
		for(char c : charSet) {
			if(!indegrees.containsKey(c)) {
				queue.add(c);
			}
		}
		return  queue;
	}
	
	public DataMap2 getDataMap(String[] words) {
		Map<Character, List<Character>> adjaceny = new HashMap<Character, List<Character>>();
		Map<Character, Integer> indegrees = new HashMap<Character, Integer>();
		
		for(int i = 0;i < words.length - 1; i++) {
			for(int j = 0;j < words[i].length(); j++) {
				if(j >= words[i + 1].length()) {
					adjaceny  = null;
					indegrees = null;
					return new DataMap2(null, null);
				}
				char c1 = words[i].charAt(j);
				char c2 = words[i + 1].charAt(j);
				
				if(c1 != c2) {
					if(adjaceny.containsKey(c1)) {
						adjaceny.get(c1).add(c2);
					} else {
						adjaceny.put(c1, new ArrayList<Character>());	
						adjaceny.get(c1).add(c2);
					}
					indegrees.put(c2, indegrees.getOrDefault(c2, 0) + 1);
					break;
				}
			}
		}
		
		return new DataMap2(adjaceny, indegrees);
	}
	
	public Set<Character> getCharSet(String[] words) {
		Set<Character> charSet = new HashSet<Character>();
		for(String word : words) {
			for(int i = 0;i < word.length(); i++) {
				char c = word.charAt(i);
				if(!charSet.contains(c)) {
					charSet.add(c);
				}
			}
		}
		return charSet;
	}
}
