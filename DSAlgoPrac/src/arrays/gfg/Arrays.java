package arrays.gfg;

public class Arrays {

	public static void main(String[] args) {
		int[] a = {0, 1};
		minimumNoOfGroupFlipsRequiredToMakeSame(a);
	}
	
	public static void reverseArray(int[] a) {
		int i = 0, j = a.length - 1;
		while(i < j) {
			swap(a, i++, j--);
		}
	}
	
	public static void removeDuplicatesFromASortedArray(int[] a) {
		int i = 0, j = 1, size = 1;
		for(; i < a.length - 1; i++) {
			for(;j < a.length; j++) {
				if(a[i] != a[j]) {
					break;
				}
			}
			if(j < a.length) {
				size++;
				a[i + 1] = a[j];
			}
		}
		print(a, size);
	}
	
	public static void moveAllZeroesToTheEnd(int[] a) {
		int i = 0,j = 0;
		while(i < a.length - 1) {
			if(a[i] != 0) {
				i++;
			} else {
				for(j = i + 1; j < a.length && a[j] == 0; j++) {}
				if(j < a.length) {
					swap(a, i++, j);
				} else {
					i = a.length;
				}
			}
		}
	}
	
	public static void printLeaders(int[] a) {
		System.out.println(a[a.length - 1]);
		int max = a[a.length - 1];
		for(int i = a.length - 2; i >= 0; i--) {
			if(max < a[i]) {
				System.out.println(a[i]);
				max = a[i];
			}
		}
	}	
	
	public static void maxDiffProblem(int[] a) {
		int n = a.length;
		int min = a[0], diff = a[1] - a[0];
		for(int i = 1;i < n - 1; i++) {
			min = Math.min(a[i], min);
			diff = Math.max(a[i + 1] - min, diff);
		}
		System.out.println(diff);
	}
	
	public static void printFrequenciesOfEachElementInASortedArray(int[] a) {
		int freq = 0, curr = a[0];
		for(int i = 0;i < a.length - 1;) {
			curr = a[i];
			int j = i;
			while(j < a.length && a[j] == a[i]) {
				j++;
			}
			if(i == j) {
				freq = 1;
				i++;
			} else {
				freq = j - i;
				i = j;
			}
			System.out.println(curr + " : " + freq);
		}
	}
	
	public static void maxProfit(int[] a) {
		int profit = 0;
		for(int i = 1;i < a.length; i++) {
			if(a[i] > a[i - 1]) {
				profit += a[i] - a[i - 1];
			}
		}
		System.out.println(profit);
	}
	
	public static void trappingRWProblem(int[] a) {
		int[] lma = getLeftMaxArray(a);
		int[] rma = getRightMaxArray(a);
		int qty = 0;
		
		for(int i = 1;i < a.length - 1; i++) {
			if(lma[i] > 0 && rma[i] > 0) {
				qty += Math.abs(Math.min(lma[i], rma[i]) - a[i]);
			}
		}
		
		System.out.println(qty);
	}
	
	
	public static void maxConsecutiveOnesInBinaryArray(int[] a) {
		int maxLength = 0;
		for(int i = 0;i < a.length;) {
			if(a[i] == 0) {
				i++;
				continue;
			} 
			int j = i;
			while(j < a.length && a[j] == 1) {
				j++;
			}
			maxLength = Math.max(maxLength, j - i);
			i = j + 1;
		}
		System.out.println(maxLength);
	}
	
	public static void maximumSumSubarray(int[] a) {
		int maxSum = Integer.MIN_VALUE, i = 0, currSum = 0;
		while(i < a.length) {
			int tempSum = currSum + a[i];
			if(tempSum > a[i]) {
				currSum = tempSum;
			} else {
				currSum = a[i];
			}
			maxSum = Math.max(maxSum, currSum);
			i++;
		}
		System.out.println(maxSum);
	}
	
	
	public static void maxLengthEvenOddSubarray(int[] a) {
		int i = 0, currLength = 1, maxLength = 1;
		while(i < a.length - 1) {
			boolean parity = (a[i] % 2 == 0) ^ (a[i + 1] % 2 == 0);
			if(!parity) {
				currLength = 1;
			} else {
				currLength++;
				maxLength = Math.max(maxLength, currLength);
			}
			i++;
		}
		System.out.println(maxLength);
	}
	
	public static void majorityElement(int[] a) {
		int res = 0, count = 1;
		for(int i = 1;i < a.length; i++) {
			if(a[res] == a[i]) {
				count++;
			} else {
				count--;
			}
			if(count == 0) {
				res = i;
				count = 1;
			}
		}
		System.out.println(res);
	}
	
	public static void minimumNoOfGroupFlipsRequiredToMakeSame(int[] a) {
		boolean flip = !((a[0] == 1) && (a[a.length - 1] == 1));
		int flipBit = (flip) ? 1 : 0;
		for(int i = 0;i < a.length; ) {
			if(a[i] != flipBit) {
				i++;
				continue;
			}
			int j = i + 1;
			while(j < a.length && a[j] == flipBit) {
				j++;
			}
			System.out.println("Flip from " + i + " to "  + (j - 1));
			i = j;
		}
	}
	
	/*
	 *########################################################################################################
	 * 												Helpers
	 *########################################################################################################
	 */
	
	public static int[] getLeftMaxArray(int[] a) {
		int[] lma = new int[a.length];
		lma[0] = -1;
		int max = a[0];
		for(int i = 1;i < a.length; i++) {
			if(a[i] < max) {
				lma[i] = max;	
			} else {
				lma[i] = -1;
			}
			max = Math.max(max, a[i]);
		}
		return lma;
	}
	
	public static int[] getRightMaxArray(int[] a) {
		int[] rma = new int[a.length];
		rma [a.length - 1] = -1;
		int max = a[a.length - 1];
		for(int i = a.length - 2;i >= 0; i--) {
			if(a[i] < max) {
				rma [i] = max;	
			} else {
				rma [i] = -1;
			}
			max = Math.max(max, a[i]);
		}
		return rma ;
	}
	
	public static void print(int[] a, int n) {
		for(int i = 0;i < n; i++) {
			System.out.println(a[i]);
		}
	}
	
	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}
