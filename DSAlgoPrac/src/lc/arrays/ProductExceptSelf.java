package lc.arrays;

public class ProductExceptSelf {

	public static void main(String[] args) {
		int[] nums = {-1,1,0,-3,3};
		int[] answer = productExceptSelfSolve(nums);
		for(int x : answer) {
			System.out.println(x);
		}
	}

	
	public static int[] productExceptSelfSolve(int[] nums) {
		int length = nums.length;
		int[] answer = initialize(length);
		updateLefts(nums, answer);
		updateRights(nums, answer);
		return answer;
	}
	
	public static void updateLefts(int[] nums, int[] answer) {
		int length = nums.length;
		int previous = 1, last = nums[0];
		for(int i = 1;i < length; i++) {
			answer[i] *= previous * last;
			previous = answer[i];
			last = nums[i];
		}
	}
	
	public static void updateRights(int[] nums, int[] answer) {
		int length = nums.length;
		int prod = nums[length - 1];
		for(int i = length - 2;i >= 0; i--) {
			answer[i] *= prod;
			prod *= nums[i];
		}
	}
	
	public static int[] initialize(int length) {
		int[] answer = new int[length];
		for(int i = 0; i < length; i++) {
			answer[i] = 1;
		}
		return answer;
	}
}
