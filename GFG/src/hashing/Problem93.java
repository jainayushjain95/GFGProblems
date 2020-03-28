package hashing;

import java.util.HashMap;
import java.util.Map;

public class Problem93 {

	public static void main(String[] args) {
		int[] a = {1, 0, 1, 1, 1, 0, 0};
		System.out.println(lengthOfLongestSubarray(a));
	}

	public static int lengthOfLongestSubarray(int[] a) {
		int maxLength = 0, preSum = 0;
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for(int i = 0; i  < a.length; i++) {
			preSum = (a[i] == 0) ? (--preSum) : (++preSum);
			
			if(hm.containsKey(preSum)) {
				maxLength = Math.max(maxLength, i - hm.get(preSum));
			}
			
			if(!hm.containsKey(preSum)) {
				hm.put(preSum, i);
			}
			
		}
		return maxLength;
	}
	
}
