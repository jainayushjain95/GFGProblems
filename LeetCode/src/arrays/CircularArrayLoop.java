package arrays;

import java.util.HashSet;

public class CircularArrayLoop {

	public static void main(String[] args) {
		int[] nums = {1, 1, 2};
		System.out.println(circularArrayLoopSolve(nums));
	}
//	https://www.youtube.com/watch?v=2hVinjU-5SA
	public static boolean circularArrayLoopSolve(int[] nums) {
		boolean isCycleExists = false;
		int n = nums.length;
		for(int i = 0; i < n; i++) {
			boolean flag = true;
			int startingIndex = i;
			int count = 0;
			do {
				int nextIndex = getNextIndex(n, nums[startingIndex], startingIndex);
				if(nums[i] > 0 && nums[nextIndex] < 0) {
					flag = false;
					break;
				} else if(nums[i] < 0 && nums[nextIndex] > 0) {
					flag = false;
					break;
				} else {
					count++;
					startingIndex = nextIndex;
				}
			} while(count < n && startingIndex != i);
			if(count > 1 && flag && startingIndex == i) {
				isCycleExists = true;
				break;
			}
			i = startingIndex;
		}
		return isCycleExists;
	}
	
	public static boolean circularArrayLoopSolveOpt1(int[] nums) {
		boolean isCycleExists = false;
		int n = nums.length;
		
		return isCycleExists;
	}
	
	public static int getNextIndex(int n, int element, int i) {
		int nextIndex = -1;
		if(element == 0) {
			nextIndex = 0;
		} else if(element > 0) {
			nextIndex = (i + element) % n;
		} else {
			nextIndex = (i + element) % n;
			if(nextIndex < 0) {
				nextIndex = n + nextIndex;
			}
		}
		return nextIndex;
	}
} 
