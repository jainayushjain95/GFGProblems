package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ThreeSum {

	public static void main(String[] args) {
		int[] nums = {-1,0,1,2,-1,-4};
		threeSumSolve(nums);
		System.out.println();
	}

	public static List<List<Integer>> threeSumSolve(int[] nums) {
		List<List<Integer>> output = new ArrayList<List<Integer>>();
		Arrays.parallelSort(nums);

		int i = 0, k = nums.length - 1;
		while(i < nums.length - 2) {
			if(i > 0 && nums[i] == nums[i - 1]) {
				i++;
				continue;
			}
			k = nums.length - 1;
			int j = i + 1;
			int sumRequired = -nums[i];
			while(j < k) {
				if(j > i + 1 && nums[j] == nums[j - 1]) {
					j++;
					continue;
				}
				if(nums[k] + nums[j] == sumRequired) {
					List<Integer> list = new ArrayList<Integer>();
					list.add(nums[i]);
					list.add(nums[j]);
					list.add(nums[k]);
					output.add(list);
					j++;
				} else if(nums[k] + nums[j] > sumRequired) {
					k--;
				} else {
					j++;
				}
			}
			i++;
		}

		return output;
	}

}
