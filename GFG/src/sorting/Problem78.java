package sorting;

public class Problem78 {

	public static void main(String[] args) {
		int[] a = {10, 80, 30, 90, 40, 50, 70};
		System.out.println(lomutoPartition(a));
	}

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
