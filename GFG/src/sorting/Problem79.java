package sorting;

public class Problem79 {

	public static void main(String[] args) {
		int[] a = {5, 3, 8, 4, 2, 7, 1, 10};
		System.out.println(hoarerPartition(a));
	}

	/*
	 * In Hoare's partition, we consider first element as pivot element
	 * if we need to partition this at some index i != n -1
	 * we can simply swap first element with ith element
	 * O(N), O(1)
	 * In Lomuto, we get pivot at its correct position in addition to partition but in 
	 * Hoare's, only partition is achieved but in terms of no of swaps, hoare;s works better
	 */
	public static int hoarerPartition(int[] a) {
		int l = 0, h = a.length - 1;
		int i = l - 1, j = h + 1;
		int pivot = a[l];
		while(true) {
			do {
				i++;
			} while(a[i] < pivot);
			do {
				j--;
			} while(a[j] > pivot);
			if(i >= j) {
				return j;
			}
			swap(a, i, j);
		}
	}
	
	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i]= a[j];
		a[j] = temp;
	}
	
}
