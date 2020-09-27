package strings;

import java.util.Arrays;

public class LeftMostNonRepeatingCharacter {

	public static void main(String[] args) {
//		System.out.println(leftMostNonRepeatingCharacter(""));
		String S = "hello";
		int index = leftMostNonRepeatingCharacter(S);
		if(index == -1) {
			System.out.println(-1);
		} else {
			System.out.println(S.charAt(index));
		}
	}

	public static int leftMostNonRepeatingCharacter(String S) {
		int output = -1;
		int[] charArray = new int[255];
		
		Arrays.fill(charArray, -1);
		
		for(int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			if(charArray[c] == -1) {
				charArray[c] = i;
			} else {
				charArray[c] = -2;
			}
		}
		
		int index = Integer.MAX_VALUE;
		
		for(int i = 0;i < 255; i++) {
			if(charArray[i] >= 0) {
				index = Math.min(index, charArray[i]);
			}
		}
		
		output = (index == Integer.MAX_VALUE) ? -1 : index;
		
		return output;
	}
}
