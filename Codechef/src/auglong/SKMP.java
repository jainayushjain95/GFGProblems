package auglong;

import java.util.Scanner;

public class SKMP {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			t--;
			str.append(solve() + "\n");
		}
		System.out.println(str.toString());
	}

	public static String solve() {
		StringBuilder str = new StringBuilder();
		String smallestAnagram = "";
		String S = sc.next();
		String P = sc.next();

		int[] alpha = new int[26];

		int p = 0;

		for(int i = 0;i < S.length(); i++) {
			alpha[S.charAt(i) - 97]++;
			if(p < P.length()) {
				char c = P.charAt(i);
				str.append(c);
				alpha[c - 97]--;
				p++;
			}
		}

		smallestAnagram = str.toString();

		int firstChar = P.charAt(0) - 97;

		for(int i = firstChar;i >= 0; i--) {
			int freq = alpha[i];
			char c = (char)(i + 97);
			
			if(i < firstChar) {
				str.insert(0, getStringExp(c + "", freq));
				smallestAnagram = str.toString();
			} else {
				for(int j = 0;j < freq; j++) {
					String fwd = smallestAnagram + c;
					String bkwd = c + smallestAnagram;
					if(fwd.compareTo(bkwd) < 0) {
						str.append(c);
						smallestAnagram = str.toString();
					} else {
						str.insert(0, c);
						smallestAnagram = str.toString();
					}
				}
			}
		}

		for(int i = firstChar + 1;i < 26 ; i++) {
			int freq = alpha[i];
			char c = (char)(i + 97);
			str.append(getStringExp(c + "", freq));
			
		}

		return str.toString();
	}
	
	public static String getStringExp(String s, int expo) {
		if(s.length() == 0) {
			return s;
		}
		if(expo == 0) {
			return "";
		}
		
		if(expo == 1) {
			return s;
		}
		
		if(expo % 2 == 0) {
			String rightHalf = getStringExp(s, expo/2);
			return rightHalf + rightHalf;
		} else {
			String rightHalf = getStringExp(s, expo/2);
			return s + rightHalf + rightHalf;
		}
	}

}
