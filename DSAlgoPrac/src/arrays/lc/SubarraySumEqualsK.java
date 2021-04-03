package arrays.lc;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

	public static void main(String[] args) {
		int[] nums = {1, 2, 3};
		int k = 3;
		System.out.println(subarraySum(nums, k));
	}

	public static int subarraySum(int[] nums, int k) {
		int subarraySum = 0;
		int[] prefixSum = new int[nums.length];
		prefixSum[0] = nums[0];
		for(int i = 1;i < nums.length; i++) {
			prefixSum[i] = prefixSum[i - 1] + nums[i];
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int i = 0;i < prefixSum.length; i++) {
			int test = prefixSum[i] - k;
			if(prefixSum[i] == k) {
				subarraySum++;
			}
			if(map.containsKey(test)) {
				subarraySum += map.get(test);
			}
			if(map.containsKey(prefixSum[i])) {
				map.put(prefixSum[i], 1 + map.get(prefixSum[i]));
			} else {
				map.put(prefixSum[i], 1);
			}
		}
		
		return subarraySum;
	}
}
