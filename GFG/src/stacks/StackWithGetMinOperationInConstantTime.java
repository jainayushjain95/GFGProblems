package stacks;

import java.util.Stack;

public class StackWithGetMinOperationInConstantTime {

	static Stack<Integer> mainStack;
	static Stack<Integer> auxillaryStack;
	
	public static void main(String[] args) {
		mainStack = new Stack<Integer>();
		auxillaryStack = new Stack<Integer>();
		push(5);
		push(10);
		push(20);
		push(2);
		push(6);
		push(4);
		pop();
		pop();
		push(2);
		pop();
		push(1);
		pop();
		pop();
		System.out.println(getMin());
	}
	
	public static void push(int x) {
		mainStack.push(x);
		if(auxillaryStack.isEmpty()) {
			auxillaryStack.push(x);
		} else if(x <= auxillaryStack.peek()) {
			auxillaryStack.push(x);
		}
	}
	
	public static int pop() {
		int x = mainStack.pop();
		if(auxillaryStack.peek() == x) {
			auxillaryStack.pop();
		}
		return x;
	}
	
	public static int getMin() {
		return auxillaryStack.peek();
	}

}
