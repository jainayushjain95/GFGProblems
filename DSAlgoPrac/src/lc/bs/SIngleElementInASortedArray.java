package lc.bs;

public class SIngleElementInASortedArray {

	//10:25
	public static void main(String[] args) {
		int[] nums = {3, 3, 4};
		singleNonDuplicate(nums);
//		System.out.println(nums[solve(nums, 0, nums.length - 1)]);
	}
	
	public static int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            boolean halvesAreEven = (hi - mid) % 2 == 0;
            if (nums[mid + 1] == nums[mid]) {
                if (halvesAreEven) {
                    lo = mid + 2;
                } else {
                    hi = mid - 1;
                }
            } else if (nums[mid - 1] == nums[mid]) {
                if (halvesAreEven) {
                    hi = mid - 2;
                } else {
                    lo = mid + 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[lo];
    }
	
	public static int solve(int[] nums, int beginIndex, int endIndex) {
		if(beginIndex < endIndex) {
			int midIndex = getMidIndex(beginIndex, endIndex);
			if(nums[midIndex] == nums[midIndex - 1]) {
				if((midIndex - beginIndex - 1) % 2 == 0) {
					return solve(nums, midIndex + 1, endIndex);
				} else {
					return solve(nums, beginIndex, midIndex - 2);
				}
				
			} else if(nums[midIndex] == nums[midIndex + 1]) {
				if((endIndex - 1 - midIndex) % 2 == 0) {
					return solve(nums, beginIndex, midIndex - 1);
				} else {
					return solve(nums, midIndex + 2, endIndex);
				}
			} else {
				return midIndex;
			}
		}
		return beginIndex;
	}
	
	
	public static int getMidIndex(int beginIndex, int endIndex) {
		return (endIndex - beginIndex)/2 + beginIndex;
	}
	

}
