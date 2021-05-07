package backtrackingprobs;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4};
		List<List<Integer>> permutations = (new Permutations()).permute(nums);
		System.out.println("A");
	}
	
	public List<List<Integer>> permute(int[] nums) {
		return permuteSolve(nums, 0);
    }
	
	public List<List<Integer>> permuteSolve(int[] nums, int beginIndex) {
		
		if(beginIndex == nums.length) {
			return new ArrayList<List<Integer>>();
		}
		
		if(beginIndex == nums.length - 1) {
			List<List<Integer>> lists = new ArrayList<List<Integer>>();
			List<Integer> list = new ArrayList<Integer>();
			list.add(nums[beginIndex]);
			lists.add(list);
			return lists;
		}
		
		List<List<Integer>> lists = permuteSolve(nums, beginIndex + 1);
		List<List<Integer>> permutations = new ArrayList<List<Integer>>();
		
		for(int i = 0;i < lists.size(); i++) {
			List<Integer> list = lists.get(i);
			for(int index = 0;index <= list.size(); index++) {
				List<Integer> permutation = new ArrayList<Integer>();
				permutation.addAll(list);
				permutation.add(index, nums[beginIndex]);
				permutations.add(permutation);
			}
		}
		
        return permutations;
    }
}
