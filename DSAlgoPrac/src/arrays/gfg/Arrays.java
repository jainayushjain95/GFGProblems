package arrays.gfg;

public class Arrays {

	public static void main(String[] args) {
		int[] a = {8, 3, 1, 2};
		maxSumInTheConfiguration(a);

	}

	public static void rotateArrayClockwiseByDElements(int[] a, int d) {
		reverseArray(a, 0, d - 1);
		reverseArray(a, d, a.length - 1);
		reverseArray(a, 0, a.length - 1);
	}
	
	public static void reverseArray(int[] a, int i, int j) {
		while(i <= j) {
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

	public static void subarrayWithGivenSumInNonNegativeElementsArray(int[] a, int sum) {
		int currSum = a[0], i = 1, j = 1;
		boolean sumExists = false;

		while(i < a.length && j < a.length) {
			if(currSum == sum) {
				sumExists = true;
				break;
			}
			if(currSum < sum) {
				currSum += a[j++];
			} else {
				currSum -= a[i++ - 1];
			}
		}

		System.out.println(sumExists);
	}

	public static void checkIfArrayHasEquilibriumPoint(int[] a) {
		boolean hasEquilibriumPoint = false;
		int[] prefixSum = getPrefixSumArray(a);
		for(int i = 0;i < a.length; i++) {
			int prevSum = (i == 0) ? 0 : prefixSum[i - 1];
			int nextSum = (i == a.length - 1) ? 0 : prefixSum[a.length - 1] - prefixSum[i];
			if(prevSum == nextSum) {
				hasEquilibriumPoint = true;
				break;
			}
		}
		System.out.println(hasEquilibriumPoint);
	}

	public static void findMaxAppearingElementInRange(int[] L, int[] R) {
		int[] prefixSum = new int[2 + Math.max(getMax(L), getMax(R))];
		for(int i = 0;i < L.length; i++) {
			prefixSum[L[i]]++;
			prefixSum[R[i] + 1]--;
		}
		prefixSum = getPrefixSumArray(prefixSum);
		int maxAppearingElementIndex = 0, count = 0;
		for(int i = 0; i < prefixSum.length; i++) {
			if(count < prefixSum[i]) {
				count = prefixSum[i];
				maxAppearingElementIndex = i;
			}
		}
		System.out.println(maxAppearingElementIndex);
	}

	public static void checkIfArrayCanBeDividedInto3EqualSumParts(int[] a) {
		boolean isExists = false;
		int[] ps = getPrefixSumArray(a);
		for(int i = 0;i < a.length - 2 && !isExists; i++) {
			int sum1 = ps[i];
			for(int j = i + 1;j < a.length - 1 && !isExists; j++) {
				int sum2 = ps[j] - sum1;
				int sum3 = ps[a.length - 1] - sum1 - sum2;
				isExists = sum1 == sum2 && sum2 == sum3;
			}
		}
		System.out.println(isExists);
	}

	public static void elementWithLeftSideSmallerAndRightSideGreater(int[] a) {
		int max = a[0];
		int[] rMinArray = rMinArray(a);
		int element = -1;

		for(int i = 1;i < a.length - 1; i++) {
			if(a[i] >= max && rMinArray[i] >= a[i]) {
				element = a[i];
				break;
			}
			max = Math.max(max, a[i]);
		}	
		System.out.println(element);
	}


	public static void smallestPositiveMissingNumber(int[] a) {
		int[] array = new int[1000001];
		for(int i = 0;i < a.length; i++) {
			if(a[i] >= 0) {
				array[a[i]]++;	
			}
		}
		int missing = 1;
		for(int i = 1;i < array.length; i++) {
			if(array[i] == 0) {
				missing = i;
				break;
			}
		}
		System.out.println(missing);
	}

	public static void maxSumInTheConfiguration(int[] a) {
		int max = 0, sum = 0, configSum = 0;
		for(int i = 0;i < a.length; i++) {
			configSum += i * a[i];
			sum += a[i];
		}
		max = configSum;
		
		for(int i = 1;i < a.length; i++) {
			int currConfigSum = (configSum - sum) + (a.length * a[i - 1]);
			max = Math.max(max, currConfigSum);
			configSum = currConfigSum;
		}
		
		System.out.println(max);
	}


	/*
	 *########################################################################################################
	 * 												Helpers
	 *########################################################################################################
	 */


	public static int[] rMinArray(int[] a) {
		int[] rMin = new int[a.length];
		rMin[a.length - 1] = Integer.MAX_VALUE;
		for(int i = a.length - 2;i >= 0; i--) {
			rMin[i] = Math.min(rMin[i + 1], a[i + 1]);
		}
		return rMin;
	}

	public static int[] getLeftMaxArray(int[] a) {
		int[] lma = new int[a.length];
		lma[0] = Integer.MIN_VALUE;
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

	public static int[] getPrefixSumArray(int[] a) {
		int[] prefixSum = new int[a.length];
		prefixSum[0] = a[0];
		for(int i = 1;i < a.length; i++) {
			prefixSum[i] = a[i] + prefixSum[i - 1];
		}
		return prefixSum;
	}

	public static int getMax(int[] a) {
		int max = Integer.MIN_VALUE;
		for(int x : a) {
			max = Math.max(max, x);
		}
		return max;
	}
	
	public static int getMaxElementIndex(int[] a) {
		int index = -1;
		int max = Integer.MIN_VALUE;
		for(int i = 0;i < a.length; i++) {
			if(max < a[i]) {
				index = i;
				max = a[i];
			}
		}
		return index;
	}

}
