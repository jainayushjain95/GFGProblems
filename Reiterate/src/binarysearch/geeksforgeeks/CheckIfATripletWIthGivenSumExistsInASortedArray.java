package binarysearch.geeksforgeeks;

public class CheckIfATripletWIthGivenSumExistsInASortedArray {

	public static void main(String[] args) {
		int[] a1 = {10, 20, 30, 40, 50};
		int[] a2 = {5, 15, 25, 35, 45, 55, 65, 75, 85};
		System.out.println(medianOfTwoSortedArrays(a1, a2));
	}

	public static double medianOfTwoSortedArrays(int[] a1, int[] a2) {
		double median = 0;
		int n1 = a1.length, n2 = a2.length;
		
		int start1 = 0, end1 = n1;
		while(start1 <= end1) {
			int i1 = getMidIndex(start1, end1);
			int i2 = getIndexInSecondArray(i1, n1, n2);
			
			int max1 = (i1 != 0) ? a1[i1 - 1] : Integer.MIN_VALUE;
			int max2 = (i2 != 0) ? a2[i2 - 1] : Integer.MIN_VALUE;
			
			int min1 = (i1 != n1) ? a1[i1] : Integer.MAX_VALUE;
			int min2 = (i2 != n2) ? a2[i2] : Integer.MAX_VALUE;
			
			if(isMedianAchieved(a1, a2, min1, max1, min2, max2)) {
				if((n1 + n2) % 2 != 0) {
					median = Math.max(max1, max2);
				} else {
					median = (Math.max(max1, max2) + Math.min(min1, min2)) / 2;
				}
				break;
			} else if(max1 > min2) {
				end1 = i1 - 1;
			} else {
				start1 = i1 + 1;
			}
			
		}
		return median;
	}
	
	
	public static int getIndexInSecondArray(int i1, int n1, int n2) {
		return (n1 + n2 + 1)/2 - i1;
	}
	
	public static int getMidIndex(int i, int j) {
		return i + (j - i)/2;
	}
	
	public static boolean isMedianAchieved(int[] a1, int[] a2, int min1, int max1, int min2, int max2) {
		return max1 <= min2 && max2 <= min1;
	}
}
