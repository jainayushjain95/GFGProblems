package recursion;

import java.util.ArrayList;

public class Problem31 {

	public static void main(String[] args) {
		String input = "abc";
		getPowerSet(input, 0, new ArrayList<String>(), "");
	}

	public static ArrayList<String> getPowerSet(String s, int index, ArrayList<String> set, String temp) {
		if(index == s.length()) {
			set.add(temp);
			return set;
		}
		getPowerSet(s, index + 1, set, temp + s.charAt(index));
		getPowerSet(s, index + 1, set, temp);
		return set;
	}

}
