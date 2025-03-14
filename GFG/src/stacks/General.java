package stacks;

import java.util.*;

class PairBESM {
    int element;
    int frequency;
    public PairBESM(int element, int frequency) {
        this.element = element;
        this.frequency = frequency;
    }
}


public class General {

    public static void main(String[] args) {
        General g = new General();
        int[] nums = {3, 3, 3};
        g.numberOfSubarrays(nums);
    }
    public long numberOfSubarrays(int[] nums) {
        return numberOfSubarraysOptimized(nums);
    }

    private long numberOfSubarraysOptimized(int[] nums) {
        long count = nums.length;
        Stack<PairBESM> stack = new Stack<>();
        for(int i = 0;i < nums.length; i++) {
            while (!stack.isEmpty() && stack.peek().element < nums[i]) {
                stack.pop();
            }
            int freq = 0;
            if (!stack.isEmpty() && stack.peek().element == nums[i]) {
                freq = stack.pop().frequency;
                count += freq;
                stack.push(new PairBESM(nums[i], freq + 1));
            } else {
                stack.push(new PairBESM(nums[i], 1));
            }
        }
        return count;
    }

}
