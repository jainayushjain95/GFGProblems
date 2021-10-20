package lc.sheet;

public class MaxConsecutiveOnes {

	public static void main(String[] args) {
		int[] nums = {0,1,1,0, 0,1,1,1,1};
		System.out.println((new MaxConsecutiveOnes().findMaxConsecutiveOnes(nums)));
	}

	public int findMaxConsecutiveOnes(int[] nums) {
		int maxConsecutiveOnes = 0;
		int currConsecutiveCount = 0;
		
		for(int i = 0;i < nums.length; i++) {
			if(nums[i] == 0) {
				currConsecutiveCount = 0;
			} else {
				currConsecutiveCount++;
				maxConsecutiveOnes = Math.max(currConsecutiveCount, maxConsecutiveOnes);
			}
		}
		return maxConsecutiveOnes;
    }
}
