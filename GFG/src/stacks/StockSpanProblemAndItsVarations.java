package stacks;

import java.util.Stack;

public class StockSpanProblemAndItsVarations {

	public static void main(String[] args) {
		int[] input = {15, 13, 12, 14, 16, 8, 6, 4, 10, 30};
		printJustPreviousGreaterElements(input);
	}

	public static void printStockSpan(int[] input) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		for(int i = 1; i < input.length; i++) {
			while(!stack.isEmpty() && input[stack.peek()] <= input[i]) {
				stack.pop();
			}
			int span = (stack.isEmpty()) ? (i + 1) : (i - stack.peek());
			System.out.println(span);
			stack.push(i);
		}
	}
	
	public static void printJustPreviousGreaterElements(int[] input) {
		System.out.println(-1);
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(input[0]);
		for(int i = 1; i < input.length; i++) {
			while(!stack.isEmpty() && stack.peek() <= input[i]) {
				stack.pop();
			}
			int output = (stack.isEmpty()) ? (-1) : (stack.peek());
			System.out.println(output);
			stack.push(input[i]);
		}
	}
	
	
	public static void printJustNextGreaterElements(int[] input) {
		System.out.println(-1);
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(input[input.length - 1]);
		for(int i = input.length - 2; i >= 0; i--) {
			while(!stack.isEmpty() && stack.peek() <= input[i]) {
				stack.pop();
			}
			int output = (stack.isEmpty()) ? (-1) : (stack.peek());
			System.out.println(output);
			stack.push(input[i]);
		}
	}
	
}
