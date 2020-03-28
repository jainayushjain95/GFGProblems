package hashing;

import java.util.HashMap;
import java.util.Map;

public class Problem90 {

	public static void main(String[] args) {
		int[] a = {1, 4, 13, -3, -10, -5};
		System.out.println(isExistsSubarayWithZeroSum(a));
	}

	public static boolean isExistsSubarayWithZeroSum(int[] a) {
		boolean ans = false;
		for(int i = 1;i < a.length; i++) {
			a[i] += a[i - 1];
		}
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for(int i = 0; i < a.length; i++) {
			if(a[i] == 0 || hm.containsKey(a[i])) {
				ans = true;
				break;
			} else {
				hm.put(a[i], 1);
			}
		}
		return ans;
	}
}
