package heaps;

import java.util.PriorityQueue;


public class Probs {

	public static void main(String[] args) {
		
	}

	public int solve1(int[] nums) {
		
		//1, 1
		int lastElement = 0;
		if(nums.length == 0) {
			return 0;
		}
		if(nums.length == 1) {
			lastElement = nums[0];
		}
		
		
		PriorityQueue<Integer> priorityQueue = prepareHeap(nums);
		
		while(priorityQueue.size() > 1) {
			int diff = priorityQueue.poll() - priorityQueue.poll();
			if(diff > 0) {
				priorityQueue.add(diff);
			}
		}
		
		if(priorityQueue.size() == 1) {
			lastElement = priorityQueue.poll();
		}
		
		return lastElement;
	}
	
	
	public PriorityQueue<Integer> prepareHeap(int[] nums) {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>((a,b)-> -a + b);
		for(int x : nums) {
			priorityQueue.add(x);
		}
		return priorityQueue;
	}
}
