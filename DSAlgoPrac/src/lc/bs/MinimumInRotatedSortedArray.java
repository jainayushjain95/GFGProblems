package lc.bs;

public class MinimumInRotatedSortedArray {

	public static void main(String[] args) {
		int[] nums = {3, 1, 1};
		System.out.println(findMinSolveWithDuplicates(nums, 0, nums.length - 1));
	}

	public static int findMinSolve(int[] nums, int beginIndex, int endIndex) {
		if(beginIndex <= endIndex) {
			int midIndex = getMidIndex(beginIndex, endIndex);
			if(midIndex > 0 && nums[midIndex] < nums[midIndex - 1]) {
				return nums[midIndex];
			}
			if(nums[beginIndex] <= nums[midIndex] && nums[midIndex] > nums[endIndex]) {
				return findMinSolve(nums, midIndex + 1, endIndex);
			} else {
				return findMinSolve(nums, beginIndex, midIndex - 1) ;
			}	
		}
		return nums[beginIndex];
	}

	public static int findMinSolveWithDuplicates(int[] nums, int beginIndex, int endIndex) {
		if(beginIndex <= endIndex) {
			int midIndex = getMidIndex(beginIndex, endIndex);
			if(nums[midIndex] < nums[endIndex]) {
				return findMinSolveWithDuplicates(nums, beginIndex, midIndex);
			} else if(nums[midIndex] == nums[endIndex]) {
				return findMinSolveWithDuplicates(nums, beginIndex, endIndex - 1);
			} else {
				return findMinSolveWithDuplicates(nums, midIndex + 1, endIndex);
			}
		}
		return nums[beginIndex];
	}

	public static int getMidIndex(int beginIndex, int endIndex) {
		return ((endIndex - beginIndex) >> 1) + beginIndex;
	}
}
