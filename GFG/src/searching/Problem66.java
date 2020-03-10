package searching;

import java.util.Arrays;

public class Problem66 {

	public static void main(String[] args) {
		int[] a = {5, 10, 20, 1, 2, 3, 4};
		System.out.println(ifPresent(a, 52));
	}

	public static boolean ifPresent(int[] a, int x) {
		int pivotIndex = findPivotElementIndex(a, 0, a.length - 1);
		
		//Array not rotated at all
		if(pivotIndex == -1) {
			return Arrays.binarySearch(a, x) != -1;
		}
		
		if(a[pivotIndex] == x) {
			return true;
		}
		
		int l = Arrays.binarySearch(a, 0, pivotIndex - 1, x);
		int r = Arrays.binarySearch(a, pivotIndex + 1, a.length - 1, x);
		
		return Arrays.binarySearch(a, 0, pivotIndex - 1, x) >= 0 || Arrays.binarySearch(a, pivotIndex + 1, a.length - 1, x) >= 0;
	}
	
	public static int findPivotElementIndex(int[] a, int beginIndex, int endIndex) {
		if(beginIndex > endIndex) {
			return -1;
		}
		
		if(beginIndex == endIndex) {
			return beginIndex;
		}
		
		int midIndex = (endIndex - beginIndex)/2 + beginIndex;
		
		if(midIndex < endIndex && a[midIndex] > a[midIndex + 1]) {
			return midIndex;
		}
		
		if(a[midIndex] < a[beginIndex]) {
			return findPivotElementIndex(a, beginIndex, midIndex - 1);
		} else {
			return findPivotElementIndex(a, midIndex + 1, endIndex);
		}
	}
	
}
