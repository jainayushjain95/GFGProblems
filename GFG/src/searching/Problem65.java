package searching;

import java.util.Arrays;

public class Problem65 {

	public static void main(String[] args) {
		int[] a = new int[Integer.MAX_VALUE/6];
		for(int i = 0;i < a.length; i++) {
			a[i] = i + 1;
		}
		
		System.out.println(findInOnInfiniteSizedSortedArray(a, 12));
	
	}
	
	public static int findInOnInfiniteSizedSortedArray(int[] a, int x) {
		if(a[0] == x) {
			return 0;
		}
		int index = 1;
		while(a[index] < x) {
			index = index * 2;
		}
		if(a[index] == x) {
			return index;
		}
		return Arrays.binarySearch(a, x);
	}
	
}
