package lc.bs;

public class FindDuplicate {

	public static void main(String[] args) {
		int[] nums = {3, 4, 2, 1, 4};
		System.out.println(findDuplicateSolveArrayModify(nums));
	}
	
	public static int findDuplicateSolveArrayModify(int[] nums) {
        int missing = -1;

        for(int i = 0;i < nums.length; i++) {
        	if(nums[Math.abs(nums[i])] < 0) {
        		missing = nums[i];
        		break;
        	}
        	nums[Math.abs(nums[i])] *= -1;
        }
        
        return Math.abs(missing);
    }	

}
