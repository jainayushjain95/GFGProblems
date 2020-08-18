package arrays;

import java.util.Stack;

public class CountNumberofTeams {

	public static void main(String[] args) {
		int[] rating = {2,5,3,4,1};
		System.out.println(numberOfTeams(rating));
	}

	public static long numberOfTeams(int[] rating) {
		long noOfTeams = 0;
		int n = rating.length;
		int[] nextGreaterArray = getNextGreaterArray(rating);
		for(int i = 0;i < n - 2; i++) {
			for(int j = i + 1; j < n - 1; j++) {
				for(int k = nextGreaterArray[j]; k < n && k != -1;) {
					if((rating[i] > rating[j]) && (rating[j] > rating[k])) {
						noOfTeams++;
					} else if((rating[i] < rating[j]) && (rating[j] < rating[k])) {
						noOfTeams++;
					}
					k = nextGreaterArray[k];
				}
			}
		}
		return noOfTeams;
	}

	public static int[] getNextSmallerArray(int[] rating) {
		int n = rating.length;
		int[] nextSmallerArray = new int[n];
		nextSmallerArray[n - 1] = -1;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(n - 1);
		for(int i = n - 2;i >= 0; i--) {
			while(!stack.isEmpty() && rating[stack.get(stack.size() - 1)] >= rating[i]) {
				stack.pop();
			}
			if(stack.isEmpty()) {
				nextSmallerArray[i] = -1;
				stack.push(i);
			} else {
				nextSmallerArray[i] = stack.get(stack.size() - 1);
				stack.push(i);
			}
		}
		return nextSmallerArray;
	}
	
	public static int[] getNextGreaterArray(int[] rating) {
		int n = rating.length;
		int[] nextGreaterArray = new int[n];
		nextGreaterArray[n - 1] = -1;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(n - 1);
		for(int i = n - 2;i >= 0; i--) {
			while(!stack.isEmpty() && rating[stack.get(stack.size() - 1)] <= rating[i]) {
				stack.pop();
			}
			if(stack.isEmpty()) {
				nextGreaterArray[i] = -1;
				stack.push(i);
			} else {
				nextGreaterArray[i] = stack.get(stack.size() - 1);
				stack.push(i);
			}
		}
		return nextGreaterArray;
	}
	
}
