package sorting;

public class Problem81 {

	public static void main(String[] args) {
		int[] a = {8, 4, 7, 9, 3, 10, 5, 1, 2, 3};
		quickSortUsingHoarersPartition(a, 0, a.length - 1);
		for(int x : a) {
			System.out.println(x);
		}
	}

	public static void quickSortUsingHoarersPartition(int[] a, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
			return;
		}
		int p = hoaresPartition(a, beginIndex, endIndex);
		quickSortUsingHoarersPartition(a, beginIndex, p);
		quickSortUsingHoarersPartition(a, p + 1, endIndex);
	}
	
	public static int hoaresPartition(int[] a, int l, int h) {
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
