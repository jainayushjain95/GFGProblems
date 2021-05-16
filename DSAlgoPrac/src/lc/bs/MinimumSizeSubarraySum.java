package lc.bs;

public class MinimumSizeSubarraySum {

	public static void main(String[] args) {
		int[] nums = {2,3,1,2,4,3};
		int target = 3;
		System.out.println(minSubArrayLenSolve(target, nums));
	}

	 public static int minSubArrayLenSolve(int target, int[] nums) {
		 int beginIndex = 0, endIndex = 0, minLength = Integer.MAX_VALUE;
		 int sum = nums[beginIndex];
		 while(beginIndex < nums.length && endIndex < nums.length) {
			 if(sum >= target) {
				 minLength = Math.min(minLength, endIndex - beginIndex + 1);
				 sum = sum - nums[beginIndex];
				 beginIndex++;
			 } else {
				 endIndex++;
				 if(endIndex < nums.length) {
					 sum = sum + nums[endIndex];
				 }
			 }
		 }
		 
		 return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
	 }
}
