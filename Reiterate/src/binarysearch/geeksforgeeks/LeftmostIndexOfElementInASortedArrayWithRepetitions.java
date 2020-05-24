package binarysearch.geeksforgeeks;

public class LeftmostIndexOfElementInASortedArrayWithRepetitions {

	public static void main(String[] args) {
		int[] a = {1, 2, 3, 3, 4, 4, 4, 4, 5, 6, 6, 6, 7, 7};
		System.out.println(solve(a, 121, 0, a.length - 1));
	}

	public static int solve(int[] a, int element, int beginIndex, int endIndex) {
		if(beginIndex > endIndex) {
			return -1;
		}
		int midIndex = getMidIndex(beginIndex, endIndex);
		if(a[midIndex] == element) {
			if(midIndex == 0 || a[midIndex] > a[midIndex - 1]) {
				return midIndex;
			} 
		}
		if(a[midIndex] > element) {
			return solve(a, element, beginIndex, midIndex);
		} else if(a[midIndex] < element) {
			return solve(a, element, midIndex + 1, endIndex);
		} else {
			return solve(a, element, beginIndex, midIndex);
		}
	}
	
	public static int getMidIndex(int i, int j) {
		return i + (j - i)/2;
	}
	
}
