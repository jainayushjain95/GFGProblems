package lc.strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class MinRemoveValidParas {

	public static void main(String[] args) {
		String s = "lee(t(c)o)de)";
		StringBuilder stringBuilder = removeInValidClosing(s);
		System.out.println(stringBuilder.toString());
	}
	
	public static StringBuilder removeInValidClosing(String s) {
		StringBuilder stringBuilder = new StringBuilder();
		int balance = 0, openSeen = 0;
		
		for(int i = 0;i < s.length(); i++) {
			char c = s.charAt(i);
			if(c == '(') {
				balance++;
				openSeen++;
			} else if(c == ')') {
				if(balance == 0) {
					continue;
				}
				balance--;
			}
			stringBuilder.append(c);
		}
		
		int openNeeded = openSeen - balance;
		;
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < stringBuilder.length(); i++) {
			char c = stringBuilder.charAt(i);
			if(c == '(') {
				openNeeded--;
				if(openNeeded < 0) {
					continue;
				}
			}
			result.append(c);
		}
		
		return stringBuilder;
	}

	public static String minRemoveToMakeValid(String s) {
		StringBuilder stringBuilder = new StringBuilder();
		String solution = "";
		if(s.length() > 0) {
			Stack<Integer> stack = new Stack<Integer>();

			Set<Integer> invalidInts = new HashSet<Integer>();

			for(int i = 0;i < s.length(); i++) {
				char c = s.charAt(i);
				if(isOpeningBrace(c)) {
					stack.push(i);
				} else if(isClosingBrace(c)) {
					if(stack.isEmpty()) {
						continue;
					} else {
						stack.pop();
					}
				}
				stringBuilder.append(c);
			}

			if(!stack.isEmpty()) {
				add(stack, invalidInts);
			}

			if(invalidInts.size() > 0) {
				for(int i = 0;i < s.length(); i++) {
					if(!invalidInts.contains(i)) {
						stringBuilder.append(s.charAt(i));
					}
				}
				solution = stringBuilder.toString();
			} else {
				solution = s;
			}
		}

		return solution;
	}

	public static void add(Stack<Integer> stack, Set<Integer> invalidInts) {
		while(!stack.isEmpty()) {
			invalidInts.add(stack.pop());
		}
	}

	public static boolean isOpeningBrace(char c) {
		return c == '(';
	}

	public static boolean isClosingBrace(char c) {
		return c == ')';
	}
}