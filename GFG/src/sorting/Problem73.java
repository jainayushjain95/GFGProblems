package sorting;

public class Problem73 {

	public static void main(String[] args) {
		int[] a = {10, 20, 30, 40, 15, 60, 5};
		insertionSort(a);
		
	}
	
	public static void insertionSort(int[] a) {
		int n = a.length;
		for(int i = 1; i < n; i++) {
			int item = a[i];
			int j;
			for(j = i - 1; j >= 0 && a[j] > item; j--) {
				a[j + 1] = a[j];
			}
			a[j + 1] = item;
		} 
	}

}
