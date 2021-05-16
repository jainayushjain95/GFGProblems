package lc.strings;

public class LCP {

	public static void main(String[] args) {
		String[] strs = {"flower","flow","flight"};
		longestCommonPrefix2(strs);
	}

	public String longestCommonPrefix(String[] strs) {
		String solution = "";
		int length = strs.length;
		if(length == 1) {
			solution = strs[0];
		} else if(length > 1) {
			int count = 0;
			String base = longestCommonPrefix(strs[0], strs[1]);
			for(int i = 2; i < length && base.length() > 0; i++) {
				base = longestCommonPrefix(base, strs[i]);
			}
			solution = base;
		}
		return solution;
	}
	
	public static String longestCommonPrefix(String s1, String s2) {
		StringBuilder solution = new StringBuilder();
		int i = 0, j = 0;
		
		while(i < s1.length() && j < s2.length()) {
			char a = s1.charAt(i);
			char b = s2.charAt(j);
			if(a == b) {
				solution.append(a);
			} else {
				break;
			}
			i++;j++;
		}
		
		return solution.toString();
	}
	
	public static String longestCommonPrefix2(String[] strs) {
		String solution = "";
		int length = strs.length;
		if(length == 1) {
			solution = strs[0];
		} else if(length > 1) {
			String prefix = strs[0];
			int count = 0;
			int i = 1;
			while(i < length) {
				while(strs[i].indexOf(prefix) != 0) {
					count++;
					prefix = prefix.substring(0, prefix.length() - 1);
				}
				i++;
			}
			solution = prefix;
			System.out.println(count);
		}
		return solution;
	}
	
	
}
