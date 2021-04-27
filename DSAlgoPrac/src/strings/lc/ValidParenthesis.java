package strings.lc;

import java.util.Stack;

public class ValidParenthesis {
//
//	public static void main(String[] args) {
//
//	}

	public boolean isValid(String s) {
		boolean isValid = true;
		Stack<Character> stack = new Stack<Character>();
		
		for(int i = 0;i < s.length() && isValid; i++) {
			char c = s.charAt(i);
			if(isOpeningBracket(c)) {
				stack.push(c);
			} else if(stack.isEmpty()) {
				isValid = false;
			} else {
				char top = stack.pop();
				if(!isMatchingBracket(top, c)) {
					isValid = false;
				}
			}
		}
		isValid = isValid && stack.isEmpty();
		
		return isValid;
	}
	
	public static boolean isOpeningBracket(char c) {
		return c == '(' || c == '{' || c == '[';
	}
	
	public static boolean isMatchingBracket(char top, char c) {
		return (c == ')' && top == '(') || (c == '}' && top == '{') || (c == ']' && top == '[');
	}

}
