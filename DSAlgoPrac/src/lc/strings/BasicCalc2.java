package lc.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BasicCalc2 {

	static Map<Character, Integer> operators = new HashMap<Character, Integer>();
	
	public static void main(String[] args) {
		System.out.println((new BasicCalc2()).calculate("1+2"));
	}
	
	public int calculate(String s) {
		initialize();
        return calculateSolve(s);
    }
	
	public static void initialize() {
		operators.put('+', 1);
		operators.put('-', 1);
		operators.put('*', 2);
		operators.put('/', 2);
	}
	
	public static int getPriority(char c) {
		return operators.get(c);
	}
	
	public static int calculateSolve(String s) {
		Stack<Character> ops = new Stack<Character>();
		Stack<Integer> numbers = new Stack<Integer>();
		
		for(int i = 0;i < s.length(); i++) {
			char c = s.charAt(i);
			if(c == ' ') {
				continue;
			} else if(isNumeric(c)) {
				StringBuilder stringBuilder = new StringBuilder();
				while(i < s.length() && isNumeric(c)) {
					stringBuilder.append(c);
					i++;
					if(i < s.length()) {
						c = s.charAt(i);	
					}
				}
				i--;
				numbers.push(getIntEquivalent(stringBuilder.toString()));
			} else if(ops.isEmpty() || (getPriority(c) > getPriority(ops.peek()))) {
				ops.push(c);
			} else {
				while(!ops.isEmpty() && getPriority(c) <= getPriority(ops.peek())) {
					int right = numbers.pop();
					int left = numbers.pop();
					int solved = applyOps(ops.pop(), left, right);
					numbers.push(solved);
				}
				ops.push(c);
			}
		}
		
		while(!ops.isEmpty()) {
			int right = numbers.pop();
			int left = numbers.pop();
			int solved = applyOps(ops.pop(), left, right);
			numbers.push(solved);
		}
 		
		return numbers.pop();
    }
	
	public static int applyOps(char op, int left, int right) {
		int solution = 0;
		if(op == '-') {
			solution = left - right;
		} else if(op == '+') {
			solution = left + right;
		} else if(op == '/') {
			solution = left / right;
		} else {
			solution = left * right;
		}
		return solution;
	}
	
	public static int getIntEquivalent(String input) {
		return Integer.parseInt(input);
	}
	
	public static boolean isNumeric(char c) {
		return c >= '0' && c <= '9';
	}

}
