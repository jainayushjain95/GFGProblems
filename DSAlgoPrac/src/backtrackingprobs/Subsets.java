package backtrackingprobs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Subsets {

	public static void main(String[] args) {
		int[] nums = {0};
		List<List<Integer>> sets = (new Subsets()).subsets(nums);
		System.out.println("A");
	}
	
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> sets = new ArrayList<List<Integer>>();
		int size = (int)Math.pow(2, nums.length) - 1;
		for(int i = 0;i <= size; i++) {
			List<Integer> set = new ArrayList<Integer>();
			int j = i;
			int index = nums.length - 1;
			while(j != 0) {
				boolean isLastBitSet = (j & 1) == 1;
				if(isLastBitSet) {
					set.add(nums[index]);
				}
				j = j >> 1;
				index--;
			}
			Collections.reverse(set);
			sets.add(set);
		}
		return sets;
    }
	
	public List<List<Integer>> subsetsSolve(int[] nums, int beginIndex) {
		if(beginIndex == nums.length - 1) {
			List<List<Integer>> lists = new ArrayList<List<Integer>>();
			List<Integer> list = new ArrayList<Integer>();
			list.add(nums[beginIndex]);
			lists.add(list);
			lists.add(new ArrayList<Integer>());
			return lists;
		}
		
		List<List<Integer>> lists = subsetsSolve(nums, beginIndex + 1);
		int size = lists.size();
		for(int i = 0;i < size; i++) {
			List<Integer> list = lists.get(i);
			List<Integer> included = new ArrayList<Integer>();
			included.add(nums[beginIndex]);
			included.addAll(list);
			lists.add(included);
		}
		
        return lists;
    }

}
