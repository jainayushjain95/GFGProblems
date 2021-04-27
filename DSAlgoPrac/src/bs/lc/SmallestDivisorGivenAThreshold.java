package bs.lc;

public class SmallestDivisorGivenAThreshold {

	public static void main(String[] args) {
		int[] nums = {1,2,5,9};
		System.out.println(smallestDivisorSolve(nums, 6));
	}

	public static int smallestDivisorSolve(int[] nums, int threshold) {
		int begin = 1, end = Integer.MIN_VALUE;
		int solution = Integer.MAX_VALUE;
		for(int x : nums) {
			end = Math.max(end, x);
		}
		while(begin <= end) {
			int mid = getMid(begin, end);
			int sum = getSum(nums, mid, threshold);
			if(sum > threshold) {
				begin = mid + 1;
			} else {
				solution = mid;
				if(mid == 1) {
					break;
				} else {
					end = mid - 1;
				}
			} 
		}
		
		return solution;
	}
	
	public static int getMid(int begin, int end) {
		return ((end - begin) >> 1) + begin;
	}
	
	public static int getSum(int[] nums, int divisor, int threshold) {
		int sum = 0;
		for(int x : nums) {
			sum += x/divisor + ((x % divisor == 0) ? 0 : 1);
            if(sum > threshold) {
                break;
            }
		}
		return sum;
	}

}
