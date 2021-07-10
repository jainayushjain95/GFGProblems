package lc.math;

import java.util.ArrayList;
import java.util.List;

public class Problems {

	public static void main(String[] args) {
		int[] nums = {-1,1};
		System.out.println((new Problems()).isArmstrong(153));
	}

	public boolean isArmstrong(int n) {
        int digits = (int)Math.floor(1 + Math.log10(n));
        int sum = 0, temp = n;
        
        while(temp > 0) {
            sum += Math.pow(temp % 10, digits);
            if(sum > n) {
                return false;
            }
            temp = temp / 10;
        }
        
        return sum == n;
    }
	
	public int[] nearestPerfectSquare(int N) {
		int[] solve = new int[2];
		if(isPerfectSquare(N)) {
			solve[0] = N;
			solve[1] = 0;
		} else {
			int leftPS = -1, rightPS = -1;
			int delta = 1;

			while(leftPS == -1 && rightPS == -1) {
				if(leftPS == -1) {
					if(isPerfectSquare(N - delta)) {
						leftPS = N - delta;
					}
				}

				if(rightPS == -1) {
					if(isPerfectSquare(N + delta)) {
						rightPS = N + delta;
					}
				}
				delta++;
			}

			if(leftPS == -1) {
				int rightDistance = rightPS - N;
				solve[0] = rightPS;
				solve[1] = rightDistance;
			} else if(rightPS == -1){
				int leftDistance = N - leftPS;
				solve[0] = leftPS;
				solve[1] = leftDistance;
			} else {
				int leftDistance = N - leftPS;
				int rightDistance = rightPS - N;

				if(leftDistance < rightDistance) {
					solve[0] = leftPS;
					solve[1] = leftDistance;
				} else {
					solve[0] = rightPS;
					solve[1] = rightDistance;
				}   
			}
		}


		return solve;
	}

	public static boolean isPerfectSquare(long n) {
		
		return Math.ceil((double)Math.sqrt(n)) ==
		        Math.floor((double)Math.sqrt(n));
		
//		long left = 1, right = x;
//		boolean isPerfectSquare = false;
//
//		while(left <= right) {
//			long mid = getMid(left, right);
//			long temp = mid * mid;
//			if(temp == x) {
//				isPerfectSquare = true;
//				break;
//			} else if(temp > x) {
//				right = mid - 1;
//			} else {
//				left = mid + 1;
//			}
//		}
//
//		return isPerfectSquare;
	}

	public static long getMid(long left, long right) {
		return (right - left)/2 + left;
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
