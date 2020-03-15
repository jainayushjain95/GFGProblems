package sorting;

public class Problem78 {

	public static void main(String[] args) {
		int[] a = {10, 3, 8, 4, 2, 7, 1, 5};
		System.out.println(lomutoPartition(a));
	}

	/*
	 * In Lomuto partition, we consider last element as pivot element
	 * if we need to partition this at some index i != n -1
	 * we can simply swap last element with ith element
	 * O(N), O(1)
	 */
	public static int lomutoPartition(int[] a) {
		int h = a.length  - 1, l = 0;
		int pivot = a[h];
		int i = -1;
		for(int j = l; j < h; j++) {
			if(a[j] < pivot) {
				swap(a, ++i, j);
			}
		}
		swap(a, i + 1, h);
		return i + 1;
	}
	
	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i]= a[j];
		a[j] = temp;
	}
	
}
