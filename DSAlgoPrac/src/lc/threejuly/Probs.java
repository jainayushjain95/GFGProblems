package lc.threejuly;

import java.util.Stack;

public class Probs {

	public static void main(String[] args) {
		int[] nums1 = {55,30,5,4,2};
		int[] nums2 = {100,20,10,10,5};
		System.out.println(new Probs().maxDistance(nums1, nums2));
	}

	public int maxDistance(int[] nums1, int[] nums2) {
		int maxDi = 0;
		int i1 = 0, i2 = 0;
		
		while(i1 < nums1.length) {
			while(i2 < nums2.length && nums2[i2] >= nums1[i1]) {
				i2++;
			}
			
			int dis = i2 - i1 - 1;
			if(dis > maxDi) {
				maxDi = dis;
			}
			i1++;
		}
		
		return maxDi;
	}
	
	public int maxWidthRamp(int[] nums) {
		int maxWidth = 0;
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = 0;i < nums.length; i++) {
			if(stack.isEmpty() || nums[stack.peek()] <= nums[i]) {
				stack.push(nums[i]);
			}
		}
		
		for(int i = nums.length - 1; i > maxWidth; i--) {
			while(!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
				int width = i - stack.pop();
				if(width > maxWidth) {
					width = maxWidth;
				}
			}
		}
		
		return maxWidth;
    }

}
