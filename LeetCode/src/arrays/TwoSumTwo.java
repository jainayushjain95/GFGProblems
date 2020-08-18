package arrays;

public class TwoSumTwo {

	public static void main(String[] args) {
		int[] nums = {2,7,11,15};
		int target = 22;
		getTwoSum(nums, target);
	}
	
	public static int[] getTwoSum(int[] nums, int target) {
		int[] output = new int[2];
		int i = 0, j = nums.length - 1;
		while(i < j) {
			if(nums[i] + nums[j] == target) {
				output[0] = i;
				output[1] = j;
				break;
			} else if(nums[i] + nums[j] > target) {
				j--;
			} else {
				i++;
			}
		}
		return output;
	}

}
