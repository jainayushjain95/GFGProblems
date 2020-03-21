package sorting;

public class Problem83 {

	public static void main(String[] args) {
		int[] a = {2, 0, 1, 2, 1, 0, 1, 2};
		segragate(a);
		for(int x : a) {
			System.out.println(x);
		}
	}

	public static void segragate(int[] a) {
		int low = 0, mid = 0, high = a.length - 1;
		while(mid <= high) {
			if(a[mid] == 0) {
				swap(a, low, mid);
				low++;
				mid++;
			} else if(a[mid] == 1) {
				mid++;
			} else {
				swap(a, high, mid);
				high--;
				mid++;
			}
		}
	}
	
	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
}
