package lc.bs;

public class FirstAndLastPosition {

	public static void main(String[] args) {
		int[] nums = {};
		int target = 10;
		int[] solution = new int[2];
		solution[0] = searchRangeLeftMost(nums, target, 0, nums.length - 1);
		solution[1] = searchRangeRightMost(nums, target, 0, nums.length - 1);
		System.out.println(solution[0]);
		System.out.println(solution[1]);
	}
	
	public static int searchRangeLeftMost(int[] nums, int target, int beginIndex, int endIndex) {
        if(beginIndex > endIndex) {
        	return -1;
        }
        int midIndex = getMidIndex(beginIndex, endIndex);
        if(nums[midIndex] < target) {
        	return searchRangeLeftMost(nums, target, midIndex + 1, endIndex);
        } else if(nums[midIndex] > target) {
        	return searchRangeLeftMost(nums, target, beginIndex, midIndex - 1);
        } else if(midIndex == 0 || nums[midIndex - 1] < target) {
        	return midIndex;
        } else {
        	return searchRangeLeftMost(nums, target, beginIndex, midIndex - 1);
        }
    }

	public static int searchRangeRightMost(int[] nums, int target, int beginIndex, int endIndex) {
		if(beginIndex > endIndex) {
        	return -1;
        }
        int midIndex = getMidIndex(beginIndex, endIndex);
        if(nums[midIndex] < target) {
        	return searchRangeRightMost(nums, target, midIndex + 1, endIndex);
        } else if(nums[midIndex] > target) {
        	return searchRangeRightMost(nums, target, beginIndex, midIndex - 1);
        } else if(midIndex == nums.length - 1 || nums[midIndex + 1] > target) {
        	return midIndex;
        } else {
        	return searchRangeRightMost(nums, target, midIndex + 1, endIndex);
        }
    }
	
	public static int getMidIndex(int beginIndex, int endIndex) {
		return (endIndex - beginIndex)/2 + beginIndex;
	}
	
}
