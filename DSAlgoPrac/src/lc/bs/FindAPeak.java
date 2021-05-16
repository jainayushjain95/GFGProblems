package lc.bs;

public class FindAPeak {

	public static void main(String[] args) {
		int[] nums = {3, 3};
		System.out.println(findPeakElementSolve(nums, 0, nums.length - 1));
	}
	
	public static int findPeakElementSolve(int[] nums, int beginIndex, int endIndex) {
		if(beginIndex <= endIndex) {
			int midIndex = getMidIndex(beginIndex, endIndex);
			if(isPeakIndex(midIndex, nums)) {
				return midIndex;
			} else if(midIndex > 0 && nums[midIndex] < nums[midIndex - 1]) {
				return findPeakElementSolve(nums, beginIndex, midIndex - 1);
			} else {
				return findPeakElementSolve(nums, midIndex + 1, endIndex);
			}
		}
		return -1;
    }
	
	public static boolean isPeakIndex(int index, int[] nums) {
		boolean flag = false;

		if(index == 0 && index == nums.length - 1) {
			flag = true;
		} else if(index == 0) {
			flag = nums[0] > nums[1];
		} else if(index == nums.length - 1) {
			flag = nums[nums.length - 1] > nums[nums.length - 2];
		} else {
			flag = nums[index] > nums[index - 1] && nums[index] > nums[index + 1];
		}
		return flag;		
	}
	
	public static int getMidIndex(int beginIndex, int endIndex) {
		return (endIndex - beginIndex)/2 + beginIndex;
	}

}
