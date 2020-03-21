package sorting;

public class Problem82 {

	public static void main(String[] args) {
		int[] a = {15, -3, -2, -4, 5, -6, 4, -9, 4, -3, 4, -5};
		segregateNegativeAndPositive(a);
		for(int x : a) {
			System.out.println(x);
		} 
	}

	public static void segregateNegativeAndPositive(int[] a) {
		int beginIndex = -1, endIndex = a.length;	
		while(beginIndex <= endIndex) {
			do {
				beginIndex++;
			} while(beginIndex < a.length && a[beginIndex] < 0);
			do {
				endIndex--;
			} while(endIndex >= 0 && a[endIndex] >= 0);
			if(beginIndex >= endIndex) 
				return;
			swap(a, beginIndex, endIndex);
		}
	}
	
	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
}
