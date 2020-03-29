package hashing;

import java.util.HashMap;
import java.util.Map;

public class Problem95 {

	public static void main(String[] args) {
//		int[] a = {1, 9, 3, 4, 2, 20};
		int[] a = {10, 6, 3, 13, 5, 2, 8, 7, 4, 9};
		System.out.println(longestConsecutiveSubsequence(a));
	}
	
	public static int longestConsecutiveSubsequence(int[] a) {
		int maxLength = 1;
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for(int x : a) {
			hm.put(x, 1);
		}
		
		for(int element : a) {
			if(!hm.containsKey(element - 1)) {
				int temp = 1;
				while(hm.containsKey(element + temp)) {
					System.out.println(element + temp);
					temp++;
				}
				maxLength = Math.max(maxLength, temp);
			}
		}
		
		return maxLength;
	}

}
