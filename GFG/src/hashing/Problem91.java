package hashing;

import java.util.HashMap;
import java.util.Map;

public class Problem91 {

	public static void main(String[] args) {
		int[] a = {5, 8, 6, 13, 3, -1};
		System.out.println(isExistsSubarayWithGivenSum(a, 13));
	}
	
	public static boolean isExistsSubarayWithGivenSum(int[] a, int sum) {
		boolean ans = false;
		int preSum = 0;
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for(int i = 0;i < a.length; i++) {
			preSum += a[i];
			if(preSum == sum || hm.containsKey(-sum + preSum)) {
				ans = true;
				break;
			}
			hm.put(preSum, 1);
		}

		return ans;
	}

}
