package stacks;

import java.util.List;
import java.util.Stack;

public class BalancedParanthesisCheck {

	public static void main(String[] args) {
	
		String input = "[{{(){}}]";
		System.out.println(isBalanced(input));
	}
	
	public static boolean isBalanced(String input) {
		List<Character> stack = new Stack<Character>();
		for(int i = 0;i < input.length(); i++) {
			char c = input.charAt(i);
			if(isOpeningBracket(c)) {
				stack.add(c);
			} else {
				char top = stack.remove(stack.size() - 1);
				if(!isParanthesisPair(top, c)) {
					break;
				}
			}
		}
		return stack.isEmpty();
	}
	
	public static boolean isOpeningBracket(char c) {
		return (c == '{' || c == '[' || c == '(');
	}
	
	public static boolean isParanthesisPair(char opening, char closing) {
		return (opening == '{' && closing == '}') || (opening == '(' && closing == ')') || (opening == '[' && closing == ']');
	}
}
