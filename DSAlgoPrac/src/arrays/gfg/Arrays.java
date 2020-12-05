package arrays.gfg;

public class Arrays {

	public static void main(String[] args) {
		int[] a = {0};
		maxConsecutiveOnesInBinaryArray(a);
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
	
	
	/*
	 * Helpers
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
