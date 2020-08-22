package arrays;

public class LeftRotateByD {

	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5};
		leftRotateArrayByD(a, 2);
		print(a);
	}

	public static void leftRotateArrayByD(int[] a, int d) {
		arrayReverse(a, 0, d - 1);
		arrayReverse(a, d, a.length - 1);
		arrayReverse(a, 0, a.length - 1);
	}
	
	public static void arrayReverse(int[] a, int beginIndex, int endIndex) {
		while(beginIndex < endIndex) {
			swap(a, beginIndex++, endIndex--);
		}
	}
	
	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void print(int[] a) {
		for(int x : a) {
			System.out.println(x);
		}
	}
}
