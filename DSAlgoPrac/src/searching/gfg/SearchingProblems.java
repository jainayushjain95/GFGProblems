package searching.gfg;

public class SearchingProblems {

	public static void main(String[] args) {
		int[] a1 = {1, 2, 3, 4, 5, 6};
		int[] a2 = {10, 20, 30, 40, 50};
		System.out.println(getMedianOfTwoSortedArrays(a1, a2, a1.length, a2.length));
	}

	public static int indexOfFirstOccurence(int[] a, int element, int beginIndex, int endIndex) {
		if(beginIndex > endIndex) {
			return -1;
		}
		int midIndex = getMid(beginIndex, endIndex);
		if(a[midIndex] > element) {
			return indexOfFirstOccurence(a, element, beginIndex, midIndex - 1);
		} else if(a[midIndex] < element) {
			return indexOfFirstOccurence(a, element, midIndex + 1, endIndex);
		} else if(midIndex == 0 || a[midIndex] != a[midIndex - 1]) {
			return midIndex;
		}
		return indexOfFirstOccurence(a, element, beginIndex, midIndex - 1);
	}

	public static int searchInSortedRotatedArray(int[] a, int beginIndex, int endIndex, int key) {
		if(beginIndex > endIndex) {
			return -1;
		}
		int midIndex = getMid(beginIndex, endIndex);
		if(a[midIndex] == key) {
			return midIndex;
		}
		if(a[beginIndex] <= a[midIndex]) {
			if(a[beginIndex] <= key && a[midIndex] > key) {
				return searchInSortedRotatedArray(a, beginIndex, midIndex - 1, key);
			} else {
				return searchInSortedRotatedArray(a, midIndex + 1, endIndex, key);
			}
		} else {
			if(a[beginIndex] >= key && a[midIndex] < key) {
				return searchInSortedRotatedArray(a, midIndex + 1, endIndex, key);
			} else {
				return searchInSortedRotatedArray(a, beginIndex, midIndex - 1, key);
			}
		}
	}
	
	public static double getMedianOfTwoSortedArrays(int[] a1, int[] a2, int n1, int n2) {
		int beginIndex1 = 0, endIndex1 = n1;
		double median = -1;
		while(beginIndex1 <= endIndex1) {
			int i1 = getMid(beginIndex1, endIndex1);
			int i2 = (n1 + n2 + 1)/2 - i1;
			
			int max1 = (i1 == 0) ? Integer.MIN_VALUE : a1[i1 - 1];
			int max2 = (i2 == 0) ? Integer.MIN_VALUE : a2[i2 - 1];
			int min1 = (i1 == n1) ? Integer.MAX_VALUE : a1[i1];
			int min2 = (i2 == n2) ? Integer.MAX_VALUE : a2[i2];
			
			if(max1 <= min2 && max2 <= min1) {
				if((n1 + n2) % 2 == 0) {
					median = ((double)Math.max(max1, max2) + (double)Math.min(min1, min2)) / 2;
					break;
				} else {
					median = (double)Math.max(max1, max2);
					break;
				}
			} else if(max1 > min2) {
				endIndex1 = i1 - 1;
			} else {
				beginIndex1 = i1 + 1;
			}
		}
		return median;
	}
	
	/*
	 *########################################################################################################
	 * 												Helpers
	 *########################################################################################################
	 */
	public static int getMid(int beginIndex, int endIndex) {
		return (endIndex - beginIndex)/2 + beginIndex;
	}
}
