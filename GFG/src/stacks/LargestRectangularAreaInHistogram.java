package stacks;

import java.util.Stack;

public class LargestRectangularAreaInHistogram {

	public static void main(String[] args) {
		int[] input = {6, 2, 5, 4, 1, 5, 6};
		System.out.println(largestAreaMethodOne(input));
	}

	/*
	 * Better Solution
	 */
	public static int largestAreaMethodOne(int[] input) {
		int max = 0;
		int[] previousSmallerArray = getJustPreviousSmallerElementsArray(input);
		int[] nextSmallerArray = getJustNextSmallerElementsArray(input);
		for(int i = 0;i < input.length; i++) {
			int currMax = input[i];
			currMax += input[i] * (i - previousSmallerArray[i] - 1);
			currMax += input[i] * (nextSmallerArray[i] - i - 1);
			max = Math.max(max, currMax);
		}
		return max;
	}
	
	
	public static int[] getJustPreviousSmallerElementsArray(int[] input) {
		int n = input.length;
		int[] output = new int[n];
		output[0] = -1;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		for(int i = 1;i < n; i++) {
			while(!stack.isEmpty() && input[i] <= input[stack.peek()]) {
				stack.pop();
			}
			output[i] = (stack.isEmpty()) ? -1 : stack.peek();
			stack.push(i);
		}
		return output;
	}
	
	public static int[] getJustNextSmallerElementsArray(int[] input) {
		int n = input.length;
		int[] output = new int[n];
		output[n - 1] = n;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(n - 1);
		for(int i = n - 2;i >= 0; i--) {
			while(!stack.isEmpty() && input[i] <= input[stack.peek()]) {
				stack.pop();
			}
			output[i] = (stack.isEmpty()) ? n : stack.peek();
			stack.push(i);
		}
		return output;
	}
	
}
