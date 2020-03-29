package hashing;

import java.util.HashMap;
import java.util.Map;

public class Problem94 {

	public static void main(String[] args) {
		int[] a = {0, 1, 0, 0, 0, 0};
		int[] b = {1, 0, 1, 0, 0, 1};
		System.out.println(longestLength(a, b));
	}
	
	public static int longestLength(int[] a, int[] b) {
		int maxLength = 0;
		for(int i = 0;i < a.length; i++) {
			a[i] = a[i] - b[i];
		}
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		
		int preSum = 0;
		
		for(int i = 0; i < a.length; i++) {
			preSum += a[i];
			if(!hm.containsKey(preSum)) {
				hm.put(preSum, i);
			} else {
				maxLength = Math.max(maxLength, i - hm.get(preSum));
			}
		}
		
		return maxLength;
	}

}
