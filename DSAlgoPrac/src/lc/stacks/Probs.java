package lc.stacks;

import java.util.*;

public class Probs {

	public static char SPACE = ' ';
	public static char openBrace = '(';
	public static char closeBrace = ')';
	public static char addOp = '+';
	public static char subtractOp = '-';

	public static void main(String[] args) {
		System.out.println(new Probs().removeKdigits("1432219", 3));
	}

	public int calculate(String s) {
		Stack<Integer> values = new Stack<Integer>();
		int number = 0, sign = 1, result = 0;
		
		for(int i = 0;i < s.length(); i++) {
			char c = s.charAt(i);
			if(c == SPACE) {
				continue;
			}
			if(isDigit(c)) {
				number = number*10 + (c - '0');
			} else if(c == addOp) {
				result = result + sign * number;
				number = 0;
				sign = 1;
			} else if(c == subtractOp) {
				result = result + sign * number;
				number = 0;
				sign = -1;
			} else if(c == openBrace) {
				values.push(result);
				values.push(sign);
				result = 0;
				sign = 1;
			} else {
				result = values.pop() * (result + sign * number) + values.pop();
				number = 0;
			}
		}
		
		if(number != 0) {
			result = result + sign * number;
		}
		return result;
	}

	public boolean isDigit(char c) {
		int val = (int)c;
		return val >= 48 && val <= 57;
	}

	public String removeKdigits(String num, int k) {
		Stack<Integer> stack = new Stack<Integer>();
		int index = 0;
		if(num.length() == k) {
			return "0";
		}
		
		while(index < num.length()) {
			int a = num.charAt(index++) - '0';
			while(!stack.isEmpty() && stack.peek() > a && k > 0) {
				stack.pop();
				k--;
			}
			stack.push(a);
		}
		
		for(int i = 0; i < k; i++) {
			stack.pop();
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		while(!stack.isEmpty()) {
			stringBuilder.append(stack.pop());
		}
		stringBuilder.reverse();
		while(stringBuilder.length() > 1 && stringBuilder.charAt(0) == '0') {
			stringBuilder.deleteCharAt(0);
		}
		return stringBuilder.toString();
    }
}
