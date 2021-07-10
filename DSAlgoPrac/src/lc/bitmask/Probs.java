package lc.bitmask;
import java.util.*;

public class Probs {

	public static void main(String[] args) {
		int[] nums = {5,1,6};
		int x = (new Probs().subsetXORSum(nums));
		System.out.println(x);
	}
	
	public int subsetXORSum(int[] nums) {
        return subsetXORSumSolve(nums, 0, 0);
    }
	
	public int subsetXORSumSolve(int[] nums, int sum, int index) {
		if(index == nums.length) {
			return sum;
		}
		return subsetXORSumSolve(nums, sum, index + 1) + subsetXORSumSolve(nums, nums[index] ^ sum, index + 1);
	}
	
	public static boolean isLastBitSet(int pos) {
		return (pos & 1) > 0;
	}
	
	public List<Integer> grayCode(int n) {
		if(n == 1) {
			List<Integer> codes = new ArrayList<Integer>();
			codes.add(0);
			codes.add(1);
			return codes;
		}
		
		List<Integer> smallerCodes = grayCode(n - 1);
		
		int mask = 1 << (n - 1);
		
		for(int i = smallerCodes.size() - 1;i >= 0 ; i--) {
			smallerCodes.add(smallerCodes.get(i) | mask);
		}
		
		
        return smallerCodes;
    }
}
