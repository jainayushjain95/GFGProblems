package hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashProbs {

	public static void main(String[] args) {
		int[][] data = {
				{1, 1},
				{2},
				{1, 1}
		};
		List<List<Integer>> wall = new ArrayList<List<Integer>>();
		
		for(int i = 0;i < data.length; i++) {
			List<Integer> list = new ArrayList<Integer>();
			for(int j = 0;j < data[i].length; j++) {
				list.add(data[i][j]);
			}
			wall.add(list);
		}
		
		System.out.println(leastBricksSolve(wall));
	}

	public static int leastBricksSolve(List<List<Integer>> wall) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        int maxFreq = 0;
        
        for(List<Integer> data : wall) {
        	int sum = 0;
        	for(int i = 0;i < data.size() - 1; i++) {
        		int x = data.get(i);
        		sum = sum + x;
        		if(freq.containsKey(sum)) {
        			freq.put(sum, freq.get(sum) + 1);
        		} else {
        			freq.put(sum, 1);
        		}
        		
        		if(freq.get(sum) > maxFreq) {
        			maxFreq = freq.get(sum);
        		}
        	}
        }
        
        return wall.size() - maxFreq;
    }
	
	
	public static void distinctElementsInEachWindow(int[] a, int k) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int i = 0;
		while(i < k) {
			if(map.containsKey(a[i])) {
				map.put(a[i], map.get(a[i]) + 1);
			} else {
				map.put(a[i], 1);
			}
			i++;
		}

		System.out.println(map.size());

		for(i = 1; i < a.length - k + 1; i++) {
			if(map.get(a[i - 1]) > 1) {
				map.put(a[i - 1], map.get(a[i - 1]) - 1);	
			} else {
				map.remove(a[i - 1]);
			}
			if(map.containsKey(a[i + k - 1])) {
				map.put(a[i + k - 1], map.get(a[i + k - 1] + 1));
			} else {
				map.put(a[i + k - 1], 1);
			}
			System.out.println(map.size());
		}
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
