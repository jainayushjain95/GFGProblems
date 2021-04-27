package strings.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GroupAnagrams {

	public static void main(String[] args) {
		String[] strs = {"eat","tea","tan","ate","nat","bat"};
		String s = "abcd";
		
		char[] ca = new char[26];
        for (char c : s.toCharArray()) ca[c - 'a']++;
        
        char[] ca1 = new char[26];
        for (char c : s.toCharArray()) ca1[c - 'a']++;
        
        String keyStr = String.valueOf(ca);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		
//		groupAnagrams(strs);
	}

	public static List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> groups = new ArrayList<List<String>>();
		Map<String, List<String>> alphasWithHashes = getAlphasWithHashes(strs);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		
		for(String hash : alphasWithHashes.keySet()) {
			groups.add(alphasWithHashes.get(hash));
		}
		
		return groups;
	}

	public static Map<String, List<String>> getAlphasWithHashes(String[] strs) {
		Map<String, List<String>> alphasWithHashes = new HashMap<String, List<String>>();
		for(String str : strs) {
			String hash = getAlphaWithHash(str);
			if(alphasWithHashes.containsKey(hash)) {
				alphasWithHashes.get(hash).add(str);
			} else {
				List<String> list = new ArrayList<String>();
				list.add(str);
				alphasWithHashes.put(hash, list);
			}
		}
		return alphasWithHashes;
	}
	
	public static String getAlphaWithHash(String str) {
		int[] alpha = new int[26];
		for(int i = 0;i < str.length(); i++) {
			alpha[str.charAt(i) - 97]++;
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i = 0;i < 26; i++) {
			stringBuilder.append("#");
			stringBuilder.append(alpha[i]);
		}
		return stringBuilder.toString();
	}

}
