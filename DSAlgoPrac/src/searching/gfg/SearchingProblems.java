package searching.gfg;

public class SearchingProblems {

	public static void main(String[] args) {
		int[] a = {10, 5, 30, 1, 2, 5, 10, 10};
		int k = 3;
		System.out.println(allocateMinimumNoOfPages(a, a.length, k));
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
	
	public static int allocateMinimumNoOfPagesRecursive(int[] a, int n, int k) {
		if(n == 1) {
			return a[0];
		}
		if(k == 1) {
			return getSum(a, 0, n - 1);
		}
		int result = Integer.MAX_VALUE;
		
		for(int i = 1;i < n; i++) {
			result = Math.min(result, Math.max(allocateMinimumNoOfPagesRecursive(a, i, k - 1), getSum(a, i, n - 1)));
		}
		
		return result;
	}
	
	/*
	 * allocateMinimumNoOfPages: start
	 */
	public static int allocateMinimumNoOfPages(int[] a, int n, int k) {
		int result = Integer.MAX_VALUE;
		int maxRange = 0, minRange = Integer.MIN_VALUE;
		
		for(int i = 0;i < n; i++) {
			maxRange += a[i];
			minRange = Math.max(minRange, a[i]);
		}
		
		while(minRange <= maxRange) {
			int midRange = getMid(minRange, maxRange);
			if(isFeasible(a, midRange, k)) {
				result = midRange;
				maxRange = midRange - 1;
			} else {
				minRange = midRange + 1;
			}
		}
		
		return result;
	}
	
	public static boolean isFeasible(int[] a, int sum, int k) {
		int count = 1, currSum = 0;
		for(int i = 0;i < a.length; i++) {
			if(currSum + a[i] <= sum) {
				currSum += a[i];
			} else {
				currSum = a[i];
				count++;
			}
		}
		return count <= k;
	}
	
	/*
	 * allocateMinimumNoOfPages: end
	 */
	
	/*
	 *########################################################################################################
	 * 												Helpers
	 *########################################################################################################
	 */
	public static int getMid(int beginIndex, int endIndex) {
		return (endIndex - beginIndex)/2 + beginIndex;
	}
	
	public static int getSum(int[] a, int beginIndex, int endIndex) {
		int sum = 0;
		while(beginIndex <= endIndex) {
			sum += a[beginIndex++];
		}
		return sum;
	}
}
