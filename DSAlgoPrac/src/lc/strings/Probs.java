package lc.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Probs {

	public static void main(String[] args) {
		String[] banned = {"hit"};
		System.out.println(new Probs().reformatDate("20th Oct 2052"));
	}
	
	public String reformatDate(String date) {
		Map<String, String> mon = new HashMap<String, String>();
		mon.put("Jan", "01");
		mon.put("Feb", "02");
		mon.put("Mar", "03");
		mon.put("Apr", "04");
		mon.put("May", "05");
		mon.put("Jun", "06");
		mon.put("Jul", "07");
		mon.put("Aug", "08");
		mon.put("Sep", "09");
		mon.put("Oct", "10");
		mon.put("Nov", "11");
		mon.put("Dec", "12");
		
		StringBuilder formattedDate = new StringBuilder();
		String year = date.substring(date.length() - 4, date.length());
		String month = date.substring(date.length() - 8, date.length() - 5);
		String day = date.substring(0, date.length() - year.length() - month.length() - 2);
		
		if(day.length() == 3) {
			day = "0" + day.substring(0, 1);
		} else if(day.length() == 4) {
			day = day.substring(0, 2);
		}
		
		formattedDate.append(year);
		formattedDate.append("-");
		
		formattedDate.append(mon.get(month));
		formattedDate.append("-");
		

		formattedDate.append(day);
		
		return formattedDate.toString();
    }
	
	public String mostCommonWord(String paragraph, String[] banned) {
		String mostCommonWord = "";
		int maxFreq = -1;
		Set<String> bannedWords = new HashSet<String>();
		Map<String, Integer> freqMap = new HashMap<String, Integer>();
		
		for(String bannedWord : banned) {
			bannedWords.add(bannedWord.toLowerCase());
		}
		
		StringBuilder wordBuffer = new StringBuilder();
		
		for(int i = 0;i < paragraph.length(); i++) {
			char c = paragraph.charAt(i);
			if(Character.isLetter(c)) {
				wordBuffer.append(Character.toLowerCase(c));
				if(i != paragraph.length() - 1) {
					continue;
				}
			}
			
			if(wordBuffer.length() > 0) {
				String wordI = wordBuffer.toString();
				if(!bannedWords.contains(wordI)) {
					int freq = 1 + freqMap.getOrDefault(wordI, 0);
					if(freq > maxFreq) {
						maxFreq = freq;
						mostCommonWord = wordI;
					}
					freqMap.put(wordI, freq);
				}
			}
			wordBuffer = new StringBuilder();
		}
		
		return mostCommonWord;
    }
	
	public int compareVersion(String version1, String version2) {
		int diff = 0, beginIndex = 0;
		String[] versions1 = version1.split("\\.");
		String[] versions2 = version2.split("\\.");
		
		while(beginIndex < versions1.length || beginIndex < versions2.length) {
			int v1 = (beginIndex < versions1.length) ? Integer.parseInt(versions1[beginIndex]) : 0;
			int v2 = (beginIndex < versions2.length) ? Integer.parseInt(versions2[beginIndex]) : 0;
			
			if(v1 > v2) {
				diff = 1;
				break;
			} else if(v1 < v2) {
				diff = -1;
				break;
			}
			
			beginIndex++;
		}
		
		return diff;
    }
	
	public String longestPrefix(String s) {
		String lps = "";
		lps = lps(s);
		return lps;
    }
	
	public String lps(String pattern) {
		int m = pattern.length();
		int[] lpsArray = new int[m];
		int j = 0;
		
		for(int i = 1;i < m; i++) {
			 if(pattern.charAt(i) != pattern.charAt(j)) {
				if(j != 0) {
					j = lpsArray[j - 1];
					i--;
				} 
			 } else {
				lpsArray[i] = ++j;
			 }
		}
		if(lpsArray[m - 1] == 0) {
			return "";
		}
		return pattern.substring(0, 1 + lpsArray[m - 1]);
	}

	public String simplifyPath(String path) {
		StringBuilder simplifiedPath = new StringBuilder();
		Stack<String> stack = new Stack<String>();
		int n = path.length();
		
		String[] paths = path.split("/");
		
		for(String pathString : paths) {
			if(pathString.equals("") || pathString.equals(".")) {
				continue;
			}
			if(pathString.equals("..")) {
				if(!stack.isEmpty()) {
					stack.pop();	
				}
			} else {
				stack.push(pathString);
			}
		}
		
		getReversedStack(stack, simplifiedPath);
		String output = simplifiedPath.toString();
		
		if(output.length() == 0) {
			output = "/";
		} else if(output.charAt(0) != '/') {
			output = "/" + output;
		}
		if(output.length() != 1 && output.charAt(output.length() - 1) == '/') {
			output = output.substring(0, output.length() - 1);
		}
		return output;
	}

	public static void getReversedStack(Stack<String> stack, StringBuilder simplifiedPath) {
		if(stack.isEmpty()) {
			return;
		}
		String data = stack.pop();
		getReversedStack(stack, simplifiedPath);
		simplifiedPath.append(data);
		simplifiedPath.append("/");
	}
	
	public static int passContinuedSlashes(String path, int i, int n) {
		int j = i + 1;
		if(j < n) {
			char c = path.charAt(j);
			while(j < n && c == '/') {
				j++;
				if(j < n) {
					c = path.charAt(j);
				}
			}	
		}
		return j - 1;
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
		Map<String, List<String>> getAlphaWithHashes = getAlphaWithHashes(strs);
		for(String str : getAlphaWithHashes.keySet()) {
			groupedAnagrams.add(getAlphaWithHashes.get(str));
		}
		return groupedAnagrams;
	}

	public static boolean areAnagrams(int[] alpha1, int[] alpha2) {
		boolean areAnagrams = true;
		for(int i = 0;i < 26; i++) {
			if(alpha1[i] != alpha2[i]) {
				areAnagrams = false;
				break;
			}
		}
		return areAnagrams;
	}

	public Map<String, List<String>> getAlphaWithHashes(String[] strs) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for(String str : strs) {
			String alpha = getAlpha(getAlpha(str));
			if(map.containsKey(alpha)) {
				map.get(alpha).add(str);
			} else {
				List<String> list = new ArrayList<String>();
				list.add(str);
				map.put(alpha, list);
			}
		}

		return map;
	}

	public static int[] getAlpha(String input) {
		int[] alpha = new int[26];
		for(int i = 0;i < input.length(); i++) {
			alpha[input.charAt(i) - 97]++;
		}
		return alpha;
	}

	public static String getAlpha(int[] alpha) {
		StringBuilder stringBuilder = new StringBuilder(); 
		for(int i = 0;i < 26; i++) {
			stringBuilder.append(alpha[i]);
			stringBuilder.append("#");
		}
		return stringBuilder.toString();
	}
}
