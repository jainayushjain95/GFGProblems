package hashing;

import java.util.HashMap;
import java.util.Map;

public class Problem92 {

	public static void main(String[] args) {
		int[] a = {5, 8, -4, -4, 9, -2, -2};
		System.out.println(maxLengthSubarayWithGivenSum(a, 0));
	}
	
	public static int maxLengthSubarayWithGivenSum(int[] a, int sum) {
		int ans = -1;
		int preSum = 0;
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for(int i = 0;i < a.length; i++) {
			preSum += a[i];
			if(preSum == sum) {
				ans = i + 1;
			}
			if(hm.containsKey(-sum + preSum)) {
				ans = Math.max(ans, i - hm.get(preSum - sum));
			}
			if(!hm.containsKey(preSum)) {
				hm.put(preSum, i);
			}
		}

		return ans;
	}
	
}
