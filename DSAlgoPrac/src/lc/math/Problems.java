package lc.math;

import java.util.ArrayList;
import java.util.List;

public class Problems {

	public static void main(String[] args) {
		int[] nums = {-1,1};
		(new Problems()).productExceptSelf(nums);
	}

	public int[] productExceptSelf(int[] nums) {
		int length = nums.length;
		
		int[] answer = new int[length];
		
		for(int i = 0;i < length; i++) {
			answer[i] = 1;
		}
		
		for(int i = 1;i < nums.length; i++) {
			answer[i] = nums[i - 1] * answer[i - 1];
		}
		
		int rightMost = nums[length - 1];
		for(int i = length - 2; i >= 0; i--) {
			int temp = nums[i] * rightMost;
			answer[i] = answer[i] * rightMost;
			rightMost = temp;
		}

		for(int x : answer) {
			System.out.println(x);
		}
		return answer;
	}

	public static int[] getLeftPrefixProduct(int[] nums) {
		int[] left = new int[nums.length];
		left[0] = 1;
		for(int i = 1;i < nums.length; i++) {
			left[i] = left[i - 1] * nums[i - 1];
		}
		return left;
	}

	public static int[] getRightPrefixProduct(int[] nums) {
		int[] right = new int[nums.length];
		right[nums.length - 1] = 1;
		for(int i = nums.length - 2;i >= 0; i--) {
			right[i] = right[i + 1] * nums[i + 1];
		}
		return right;
	}
	
	public int kthFactor(int n, int k) {
		int kthfactor = -1;
		List<Integer> divisors = new ArrayList<Integer>();
		
		int upperLimit = (int)Math.sqrt(n);
		
		for(int i = 1; i <= upperLimit && k != 0; i++) {
			if(n % i == 0) {
				divisors.add(i);
				k--;
			}
			if(k == 0) {
				kthfactor = i;
			}
		}
		
		if(k != 0) {
			if(upperLimit * upperLimit == n) {
				k++;
			}
			
			if(k <= divisors.size()) {
				kthfactor = n/(divisors.get(-k + divisors.size()));
			}	
		}
		
		System.out.println(kthfactor);
		return kthfactor;
    }
}
