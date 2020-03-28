package hashing;

import java.util.HashMap;
import java.util.Map;

public class Problem88 {

	public static void main(String[] args) {
		int[] a = {10, 15, 20, 15, 30, 30, 5};
		int[] b = {30, 5, 30, 80};
		System.out.println(countDistinctCommonElements(a, b));
	}

	public static int countDistinctCommonElements(int[] a, int[] b) {
		int count = 0;
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for(int i = 0; i < a.length; i++) {
			if(!hm.containsKey(a[i])) {
				hm.put(a[i], 1);
			}
		}
		
		for(int i = 0; i < b.length; i++) {
			if(hm.containsKey(b[i])) {
				count++;
				hm.remove(b[i]);
			}
		}
		
		return count;
	}
	
}
