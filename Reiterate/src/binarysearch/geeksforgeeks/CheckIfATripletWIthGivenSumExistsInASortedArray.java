package binarysearch.geeksforgeeks;

import java.util.Arrays;
import java.util.*;

public class CheckIfATripletWIthGivenSumExistsInASortedArray {

	public static void main(String[] args) {
		int[] a1 = {-1,2,1,-4};
		int[] a2 = {3, 4};
		System.out.println(new CheckIfATripletWIthGivenSumExistsInASortedArray().threeSumClosest(a1, 1));
	}

	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int closest = target, minDifference = Integer.MAX_VALUE;
		for(int i = 0;i < nums.length - 2; i++) {
			int j = i + 1, k = nums.length - 1;
			int newTarget = target - nums[i];
			while(j < k) {
				int touple = nums[j] + nums[k];
				if(touple == newTarget) {
					return target;
				} else if(touple > newTarget) {
					int currentDifference = Math.abs(target - (touple + nums[i]));
					if(minDifference > currentDifference) {
						minDifference = currentDifference;
						closest = touple + nums[i];
					}
					k--;
				} else {
					int currentDifference = Math.abs(target - (touple + nums[i]));
					if(minDifference > currentDifference) {
						minDifference = currentDifference;
						closest = touple + nums[i];
					}
					j++;
				}
			}
		}
		return closest;
	}

	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> output = new ArrayList<>();
		for(int i = 0;i <= nums.length - 3; i++) {
			int target = -nums[i];
			int j = i + 1, k = nums.length - 1;
			while(j < k) {
				if(nums[j] + nums[k] == target) {
					List<Integer> smallerOutput = new ArrayList<>();
					smallerOutput.add(nums[i]);
					smallerOutput.add(nums[j]);
					smallerOutput.add(nums[k]);
					output.add(smallerOutput);
				} else if(nums[j] + nums[k] > target) {
					k--;
				} else {
					j++;
				}
			}
		}
		return output;
	}

	public static double minmaxGasDist(int[] stations, int k) {
		return minmaxGasDistBruteForce(stations, k);
	}

	public static double minmaxGasDistBruteForce(int[] stations, int k) {
		int[] partsCount = new int[stations.length - 1];
		Arrays.fill(partsCount, 1);
		for(int i = 1;i <= k; i++) {
			int placedIndex = -1;
			double maxSize = -1;
			for(int stationIndex = 0; stationIndex < stations.length - 1; stationIndex++) {
				double gap = stations[stationIndex + 1] - stations[stationIndex];
				double sizeOfEach = gap/((double)(partsCount[stationIndex]));
				if(maxSize < sizeOfEach) {
					maxSize = sizeOfEach;
					placedIndex = stationIndex;
				}
			}
			partsCount[placedIndex]++;
		}
		return getMax(partsCount, stations);
	}

	private static double getMax(int[] partsCount, int[] stations) {
		double max = -1;
		for(int i = 0;i < partsCount.length; i++) {
			double gap = stations[i + 1] - stations[i];
			double size = gap / ((double)(partsCount[i]));
			max = Math.max(max, size);
		}
		return max;
	}

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n1 = nums1.length, n2 = nums2.length;
		int left = 0, right = n1;
		double median = 0;
		while(left <= right) {
			int partitionIndex1 = left + (right - left)/2;
			int partitionIndex2 = (n1 + n2 + 1)/2 - partitionIndex1;
			int max1 = (partitionIndex1 > 0) ? nums1[partitionIndex1 - 1] : Integer.MIN_VALUE;
			int max2 = (partitionIndex2 > 0) ? nums2[partitionIndex2 - 1] : Integer.MIN_VALUE;

			int min1 = (partitionIndex1 < n1) ? nums1[partitionIndex1] : Integer.MAX_VALUE;
			int min2 = (partitionIndex2 < n2) ? nums2[partitionIndex2] : Integer.MAX_VALUE;

			if(max1 <= min2 && max2 <= min1) {
				if((n1 + n2) % 2 == 0) {
					median = (Math.max(max1, max2) + Math.min(min1, min2))/2.0;
				} else {
					median = Math.max(max1, max2);
				}
				break;
			} else if(max1 > min2) {
				right = partitionIndex1 - 1;
			} else {
				left = partitionIndex1 + 1;
			}
		}
		return median;
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
