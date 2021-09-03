package lc.strings;

import java.util.*;

class Master {
	public int guess(String input) {
		return 1;
	}
}

public class Probs {

	/*
	 * https://www.geeksforgeeks.org/maximum-sum-subsequence-of-length-k/
	 * https://leetcode.com/problems/subarray-sum-equals-k/
	 * https://leetcode.com/problems/string-compression/
	 */
	public static void main(String[] args) {
		List<Integer> cityFrom = new ArrayList<Integer>();
		cityFrom.add(1);
		cityFrom.add(1);
		cityFrom.add(1);
		cityFrom.add(2);
		cityFrom.add(3);

		List<Integer> cityTo = new ArrayList<Integer>();
		cityTo.add(2);
		cityTo.add(5);
		cityTo.add(3);
		cityTo.add(4);
		cityTo.add(5);

		System.out.println(order(5, cityFrom, cityTo, 1));;
	}

	public String removeDuplicates(String s) {
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < s.length(); i++) {
			if(sb.length() == 0) {
				sb.append(s.charAt(i));
			} else if(sb.charAt(sb.length() - 1) != s.charAt(i)){
				sb.append(s.charAt(i));
			} else {
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

	public static List<Integer> order(int cityNodes, List<Integer> cityFrom, List<Integer> cityTo, int company) {
		List<Integer> solution = new ArrayList<>();
		Map<Integer, Queue<Integer>> map = createMap(cityFrom, cityTo);
		Set<Integer> visited = new HashSet<>();
		visited.add(company);
		Queue<Integer> queue = new LinkedList<>();
		queue.add(company);

		if(map.containsKey(company)) {
			while(!queue.isEmpty()) {
				int queueSize = queue.size();
				PriorityQueue<Integer> pq = new PriorityQueue<>();
				for(int i = 0;i < queueSize; i++) {
					int current = queue.poll();
					if(map.containsKey(current)) {
						Queue<Integer> adjacents = map.get(current);
						while(!adjacents.isEmpty()) {
							int adjacent = adjacents.poll();
							if(!visited.contains(adjacent)) {
								visited.add(adjacent);  
								pq.add(adjacent); 
								queue.add(adjacent);
							}
						}   
					}
				}
				while(!pq.isEmpty()) {
					solution.add(pq.poll());
				}
			}
		}


		return solution;
	}

	public static Map<Integer, Queue<Integer>> createMap(List<Integer> cityFrom, List<Integer> cityTo) {
		Map<Integer, Queue<Integer>> map = new HashMap<>();
		for(int i = 0;i < cityFrom.size(); i++) {
			int source = cityFrom.get(i);
			int destination = cityTo.get(i);

			if(map.containsKey(source)) {
				map.get(source).add(destination);
			} else {
				Queue<Integer> pq = new LinkedList<>();
				pq.add(destination);
				map.put(source, pq);
			}

			if(map.containsKey(destination)) {
				map.get(destination).add(source);
			} else {
				Queue<Integer> pq = new LinkedList<>();
				pq.add(source);
				map.put(destination, pq);
			}
		}
		return map;
	}

	public static void base62Encode() {
		String s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String hash = "";
		int deci = (int)(1 + Math.random() * 1000);
		while(deci > 0) {
			hash = s.charAt(deci % 62) + hash;
			deci = deci / 62;
		}
		System.out.println(hash);
	}

	//	public List<List<Integer>> permute(int[] nums) {
	//		List<List<Integer>> perms = new ArrayList<List<Integer>>();
	//				return permuteSolve(nums, 0);
	//	}


	public int[] getModifiedArray(int length, int[][] updates) {
		int[] arr = new int[length];

		for(int i = 0;i < updates.length; i++) {
			arr[updates[i][0]] += updates[i][2];
			if(updates[i][1] + 1 < length) {
				arr[updates[i][1] + 1] -= updates[i][2];
			}
		}

		performPrefixSumOp(arr);

		return arr;

	}


	public void performPrefixSumOp(int[] arr) {
		for(int i = 1; i < arr.length; i++) {
			arr[i] += arr[i - 1];
		}
	}

	public int lengthOfLongestSubstring(String s) {
		if(s.length() <= 1) {
			return s.length();
		}
		int[] alpha = new int[256];
		int maxLength = 0, beginIndex = 0, endIndex = 0;

		for(int i = 0;i < 256; i++) {
			alpha[i] = -1;
		}

		while(endIndex < s.length()) {
			if(alpha[s.charAt(endIndex)] == -1) {
				alpha[s.charAt(endIndex)] = endIndex;
			} else {
				beginIndex = Math.max(beginIndex , 1 + alpha[s.charAt(endIndex)]);
				alpha[s.charAt(endIndex)] = endIndex;
			}
			maxLength = Math.max(endIndex - beginIndex + 1, maxLength);
			endIndex++;
		}

		return maxLength;
	}

	public void findSecretWord(String[] wordlist, Master master) {

		for(int trials = 0, guessMatch = 0; trials < 10 && guessMatch < 6; trials++) {

			HashMap<String, Integer> map = new HashMap<>();
			map.key
			for (String w1 : wordlist)
				for (String w2 : wordlist)
					if (match(w1, w2) == 0)
						map.put(w1, map.getOrDefault(w1 , 0) + 1);

			String guessedWord = "";
			int minCount = 100;

			for (String w : wordlist)
				if (map.getOrDefault(w, 0) < minCount) {
					guessedWord = w;
					minCount = map.getOrDefault(w, 0);
				}

			guessMatch = master.guess(guessedWord);
			List<String> wordlist2 = new ArrayList<String>();
			for (String w : wordlist)
				if (match(guessedWord, w) == guessMatch)
					wordlist2.add(w);
			wordlist = wordlist2.toArray(new String[0]);
		}
	}

	public int match(String a, String b) {
		int matches = 0;
		for (int i = 0; i < a.length(); ++i)
			if (a.charAt(i) == b.charAt(i))
				matches ++;
		return matches;
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
