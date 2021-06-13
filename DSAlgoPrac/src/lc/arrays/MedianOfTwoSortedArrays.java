package lc.arrays;

public class MedianOfTwoSortedArrays {

	public static void main(String[] args) {
		int[] nums1 = {1, 3};
		int[] nums2 = {2};
		System.out.println(findMedianSortedArrays(nums1, nums2));
	}

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		double median = 0;
		int m = nums1.length, n = nums2.length;
		
		
		if(m > n) {
			int[] temp = nums2;
			nums2 = nums1;
			nums1 = temp;
			m = nums1.length; 
			n = nums2.length;
		}
		
		int beginIndex = 0, endIndex = m;
		
		while(beginIndex <= endIndex) {
			int i = getMid(beginIndex, endIndex);
			int j = (m + n + 1)/2 - i;
			
			int max1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
			int max2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
			int min1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
			int min2 = (j == n) ? Integer.MAX_VALUE : nums2[j];
			
			if(max1 <= min2 && max2 <= min1) {
				if((m + n) % 2 == 0) {
					median = ((double)Math.max(max1, max2) + ((double)Math.min(min1, min2)))/2;
					break;
				} else {
					median = (double)Math.max(max1, max2);
					break;
				}
			} else if(max1 > min2) {
				endIndex = i - 1;
			} else {
				beginIndex = i + 1;
			}
			
		}	
		
		return median;
	}
	

	public static int getMid(int beginIndex, int endIndex) {
		return (endIndex - beginIndex)/2 + beginIndex;
	}
	
}
