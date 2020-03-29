package hashing;

import java.util.HashSet;
import java.util.Set;

public class Problem96 {

	public static void main(String[] args) {
		int[] a = {10, 20, 20, 10, 30, 40, 10};
		printDistinctElementsInEveryWindow(a, 4);
	}

	public static void printDistinctElementsInEveryWindow(int[] a, int k) {
		int totalNumberOfWindows = a.length - k + 1;
		Set<Integer> set = new HashSet<Integer>();
		
		for(int i = 0; i < totalNumberOfWindows;i++) {
			int j = i;
			while(j < k) {
				if(!set.contains(a[j])) {
					set.add(a[j]);
				}
				j++;
			}
			System.out.println(set.size());
			set.remove(a[i]);
			k ++;
		}
	}
	
}
