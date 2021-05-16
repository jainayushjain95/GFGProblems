package lc.bs;

import java.util.HashMap;
import java.util.Map;

public class FourSum {

	public static void main(String[] args) {
		int[] A = {1, 2};
		int[] B = {-2, -1};
		int[] C = {-1, 2};
		int[] D = {0, 2};
		System.out.println(fourSumCountSolve(A, B, C, D));
	}

	public static int fourSumCountSolve(int[] A, int[] B, int[] C, int[] D) {
		int count = 0;
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		
		for(int x : A) {
			for(int y : B) {
				hm.put(x + y, hm.getOrDefault(x + y, 0) + 1);
			}
		}
		
		for(int x : C) {
			for(int y : D) {
				count = count + hm.getOrDefault(-x - y, 0);
			}
		}
		
		return count;
	}

}
