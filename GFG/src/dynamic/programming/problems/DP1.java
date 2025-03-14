package dynamic.programming.problems;

import java.util.Arrays;

public class DP1 {

	static int MAX = 100;
	static int[] dpArray = new int[MAX + 1];

	static int[][] takeCharactersDPArray;

	public static void main(String[] args) {

		int[] nums = {1,3,1,3,100};
		System.out.println(new DP1().takeCharacters("aabaaaacaabc", 2));
	}

	public int takeCharacters(String s, int k) {
		return takeCharactersSolveSlidingWindow(s, k);
	}

	public int takeCharactersSolveSlidingWindow(String s, int k) {
		int[] overallFrequency = new int[3];

		for (char c : s.toCharArray()) {
			overallFrequency[c - 'a']++;
		}

		if (overallFrequency[0] < k || overallFrequency[1] < k || overallFrequency[2] < k) {
			return -1;
		}

		int[] frequency = new int[3];
		int left = 0, right = 0, maxLength = -1;

		while(right < s.length()) {
			while(left <= right &&
					(
							(overallFrequency[0] - frequency[0] < k) ||
									(overallFrequency[1] - frequency[1] < k) ||
									(overallFrequency[2] - frequency[2] < k))
			) {
			}
			maxLength = Math.max(maxLength, right - left + 1);
			right++;
		}

		return s.length() - maxLength;
	}


	public int takeCharactersSolveRecursive(String s, int k) {
		int[] count = new int[3];

		for (char c : s.toCharArray()) {
			count[c - 'a']++;
		}

		if (count[0] < k || count[1] < k || count[2] < k) {
			return -1;
		}

		return takeCharactersSolveRecursive1(s, k, k, k, 0, s.length() - 1);

	}
	public int takeCharactersSolveRecursive1(String s, int ka, int kb, int kc, int beginIndex, int endIndex) {
		System.out.println(beginIndex + " : " + endIndex);
		if(beginIndex < 0 || endIndex < beginIndex) {
			return 0;
		}

		if(ka + kb + kc == 0) {
			return 0;
		}
		int pickFromLeft;
		int pickFromRight;

		if(s.charAt(beginIndex) == 'a') {
			int tempKA = (ka > 0) ? ka - 1: ka;
			pickFromLeft = takeCharactersSolveRecursive1(s, tempKA, kb, kc, beginIndex + 1, endIndex);
		} else if(s.charAt(beginIndex) == 'b') {
			int tempKB = (kb > 0) ? kb - 1: kb;
			pickFromLeft = takeCharactersSolveRecursive1(s, ka, tempKB, kc, beginIndex + 1, endIndex);
		} else {
			int tempKC = (kc > 0) ? kc - 1: kc;
			pickFromLeft = takeCharactersSolveRecursive1(s, ka, kb, tempKC, beginIndex + 1, endIndex);
		}


		if(s.charAt(endIndex) == 'a') {
			int tempKA = (ka > 0) ? ka - 1: ka;
			pickFromRight = takeCharactersSolveRecursive1(s, tempKA, kb, kc, beginIndex, endIndex - 1);
		} else if(s.charAt(endIndex) == 'b') {
			int tempKB = (kb > 0) ? kb - 1: kb;
			pickFromRight = takeCharactersSolveRecursive1(s, ka, tempKB, kc, beginIndex, endIndex - 1);
		} else {
			int tempKC = (kc > 0) ? kc - 1: kc;
			pickFromRight = takeCharactersSolveRecursive1(s, ka, kb, tempKC, beginIndex, endIndex - 1);
		}


		return 1 + Math.min(pickFromLeft, pickFromRight);
	}

	public int rob(int[] nums) {
		dpArray = new int[nums.length];
		Arrays.fill(dpArray, -1);
		return solve2(nums, 0, false);
	}

	private int solve2(int[] nums, int index, boolean firstHouseRobbed) {
		if(index >= nums.length) {
			return 0;
		}

		if(dpArray[index] >= 0) {
			return dpArray[index];
		}
		if(index == nums.length - 1) {
			return (firstHouseRobbed) ? 0 : nums[index];
		}

		if(index == 0) {
			dpArray[index] = Math.max(nums[index] + solve2(nums, index + 2, true), solve2(nums, index + 1, false));
			return dpArray[index];
		}

		dpArray[index] = Math.max(nums[index] + solve2(nums, index + 2, firstHouseRobbed),
				solve2(nums, index + 1, firstHouseRobbed));
		return dpArray[index];
	}


	public static int getNthFibonacciNumberRecursive(int n) {
		if(n == 0 || n == 1) {
			return n;
		}
		return getNthFibonacciNumberRecursive(n - 1) + getNthFibonacciNumberRecursive(n - 2);
	}
	
	//Memoization: Top down
	public static int getNthFibonacciNumberDP(int n) {
		if(dpArray[n] == -1) {
			if(n == 0 || n == 1 ) {
				dpArray[n] = n;
			} else {
				dpArray[n] = getNthFibonacciNumberDP(n - 1) + getNthFibonacciNumberDP(n - 2);
			}
		}
		
		return dpArray[n];
	}
	
	//Tabulation: Bottom Up
	public static int getNthFibonacciNumberDP2(int n) {
		int[] fib = new int[n + 1];
		fib[0] = 0;
		fib[1] = 1;
		for(int i = 2; i <= n; i++) {
			fib[i] = fib[i - 1] + fib[i - 2];
		}
		return fib[n];
	}
}
