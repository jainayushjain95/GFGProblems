package arrays.gfg;

public class Arrays {

	public static void main(String[] args) {
		int[] a = {0, 0, 1, 0, 0};
		moveAllZeroesToTheEnd(a);
		print(a, a.length);
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
	
	
	/*
	 * Helpers
	 */
	
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
