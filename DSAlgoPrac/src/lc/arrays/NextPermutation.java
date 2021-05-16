package lc.arrays;

public class NextPermutation {

	public static void main(String[] args) {
//		int[] nums = {2, 3, 1, 3, 3};
		int[] nums = {6, 2, 1, 5, 4, 3, 0};
		nextPermutation(nums);
		for(int i : nums) {
			System.out.println(i);
		}
	}

	public static void nextPermutation(int[] nums) {
        int n = nums.length;
        if(n > 1) {
            int index = getFirstNonDecreasingIndex(nums);
            if(index == -1) {
                reverse(nums, 0 , n - 1);    
            } else {
                int i = getFirstGreaterIndex(nums, index);
                swap(nums, i, index);
                reverse(nums, index + 1, n - 1); 
            }   
        }
    }
    
    public static int getFirstGreaterIndex(int[] nums, int index) {
        int output = -1;
        
        return output;
    }
    
    public static int getFirstNonDecreasingIndex(int[] nums) {
        int index = -1;
        for(int i = nums.length - 1; i > 0; i--) {
            if(nums[i] > nums[i - 1]) {
                index = i - 1;
                break;
            }
        }
        return index;
    }
    
    public static void reverse(int[] nums, int beginIndex, int endIndex) {
        while(endIndex > beginIndex) {
            swap(nums, beginIndex++, endIndex--);
        }
    }
    
    public static void swap(int[] nums, int beginIndex, int endIndex) {
        int temp = nums[beginIndex];
        nums[beginIndex] = nums[endIndex];
        nums[endIndex] = temp;
    }

}
