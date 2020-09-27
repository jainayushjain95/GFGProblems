package strings;

import java.util.HashSet;

public class LeftMostRepeatingCharacter {

	public static void main(String[] args) {
		System.out.println(leftMostRepeatingCharacter("geeks"));
	}

	public static int leftMostRepeatingCharacter(String S) {
		int output = -1;
		HashSet<Character> hs = new HashSet<Character>();
		for(int i = S.length() - 1;i >= 0; i--) {
			char c = S.charAt(i);
			if(hs.contains(c)) {
				output = i;
			} else {
				hs.add(c);
			}
		}
		return output;
	}
	
}
