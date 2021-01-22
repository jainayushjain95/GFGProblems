package hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashProbs {

	public static void main(String[] args) {
		int[] a = {1, 9, 3, 4, 2, 20};
		int[] b = {1, 0, 1, 0, 0, 1};
		longestConsecutiveSubsequence(a);
	}
	
	public static void longestConsecutiveSubsequence(int[] a) {
		Set<Integer> set = new HashSet<Integer>();
		for(int i = 0;i < a.length; i++) {
			set.add(a[i]);
		}
		int maxLength = 0;
		for(int i = 0;i < a.length; i++) {
			if(!set.contains(a[i] - 1)) {
				int currLen = 0, curr = a[i];
				while(set.contains(curr)) {
					currLen++;
					curr++;
				}
				maxLength = Math.max(maxLength, currLen);
			}
		}
		System.out.println(maxLength);
	}

	public static void longestSubarrayWithSame0sAnd1sInBinaryArray(int[] a) {
		for(int i = 0;i < a.length; i++) {
			if(a[i] == 0) {
				a[i] = -1;
			}
		}
		int maxLength = 0;
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int prefixSum = 0;

		for(int i = 0;i < a.length; i++) {
			prefixSum += a[i];
			if(prefixSum == 0) {
				maxLength = Math.max(maxLength, i + 1);
			}
			if(hm.containsKey(prefixSum)) {
				maxLength = Math.max(maxLength, i - hm.get(prefixSum));
			}
			if(!hm.containsKey(prefixSum)) {
				hm.put(prefixSum, i);
			}
		}
		System.out.println(maxLength);
	}

	public static void longestSubarrayWithGivenSum(int[] a, int sum) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int prefixSum = 0;
		int maxLength = 0;
		for(int i = 0;i < a.length; i++) {
			prefixSum += a[i];
			if(prefixSum == sum) {
				maxLength = i + 1;
			}
			if(!map.containsKey(prefixSum)) {
				map.put(prefixSum, i);
			}
			if(map.containsKey(prefixSum - sum)) {
				maxLength = Math.max(maxLength, i - map.get(prefixSum - sum));
			}
		}
		System.out.println(maxLength);
	}

	public static void subarrayWithGivenSum(int[] a, int sum) {
		Set<Integer> set = new HashSet<Integer>();
		int prefixSum = 0;
		boolean hasSubarray = false;
		for(int i = 0;i < a.length && !hasSubarray; i++) {
			prefixSum += a[i];
			set.add(prefixSum);
			hasSubarray = prefixSum == sum || set.contains(prefixSum - sum);
		}
		System.out.println(hasSubarray);
	}

	public static void pairWithGivenSumInUnsortedArray(int[] a, int sum) {
		Set<Integer> set = new HashSet<Integer>();
		boolean hasPair = false;

		for(int i = 0; i < a.length && !hasPair; i++) {
			if(set.contains(sum - a[i])) {
				hasPair = true;
			} else {
				set.add(a[i]);
			}
		}
		System.out.println(hasPair);
	}
}