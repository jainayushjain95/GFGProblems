package hashing;

import java.util.HashMap;
import java.util.Map;

public class Problem89 {

	public static void main(String[] args) {
		int[] a = {3, 2, 8, 15, -8};
		System.out.println(isSumPairExists(a, 531));
	}

	public static boolean isSumPairExists(int[] a, int sum) {
		boolean ans = false;
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		hm.put(a[0], 1);
		for(int i = 1; i < a.length; i++) {
			if(hm.containsKey(sum - a[i])) {
				ans = true;
				break;
			} else {
				hm.put(a[i], 1);
			}
		}
		return ans;
	}
	
}
