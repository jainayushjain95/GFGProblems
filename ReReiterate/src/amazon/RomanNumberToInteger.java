package amazon;

import java.util.HashMap;
import java.util.Scanner;

public class RomanNumberToInteger {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = Integer.parseInt(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		while(t != 0) {
			t--;
			sb.append(solve(sc.nextLine()));
		}
		System.out.println(sb.toString());
	}
	
	public static String solve(String S) {
		int solution = 0;
		
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		
		hm.put("I", 1);
		hm.put("V", 5);
		hm.put("X", 10);
		
		hm.put("IV", 4);
		hm.put("IX", 9);
		
		hm.put("XL", 40);
		hm.put("XC", 90);
		
		hm.put("CD", 400);
		hm.put("CM", 900);
		
		hm.put("L", 50);
		hm.put("C", 100);
		hm.put("D", 500);
		hm.put("M", 1000);
		
		
		for(int i = 0;i < S.length(); i++) {
			if((S.charAt(i) + "").equals("I")) {
				if(i + 1 < S.length()) {
					if((S.charAt(i + 1) + "").equals("V")) {
						solution += hm.get("IV");
						i++;
					} else if((S.charAt(i + 1) + "").equals("X")) {
						solution += hm.get("IX");
						i++;
					} else {
						solution += hm.get("I");	
					}
				} else {
					solution += hm.get("I");
				}	
			} else if((S.charAt(i) + "").equals("X")) {
				if(i + 1 < S.length()) {
					if((S.charAt(i + 1) + "").equals("L")) {
						solution += hm.get("XL");
						i++;
					} else if((S.charAt(i + 1) + "").equals("C")) {
						solution += hm.get("XC");
						i++;
					} else {
						solution += hm.get("X");	
					}
				} else {
					solution += hm.get("X");
				}	
			} else if((S.charAt(i) + "").equals("C")) {
				if(i + 1 < S.length()) {
					if((S.charAt(i + 1) + "").equals("D")) {
						solution += hm.get("CD");
						i++;
					} else if((S.charAt(i + 1) + "").equals("M")) {
						solution += hm.get("CM");
						i++;
					} else {
						solution += hm.get("C");	
					}
				} else {
					solution += hm.get("C");
				}	
			} else {
				solution += hm.get(S.charAt(i) + "");
			}
		}
		
		return solution + "\n";
	}
	
}
