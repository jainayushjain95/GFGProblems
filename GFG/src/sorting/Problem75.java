package sorting;

public class Problem75 {

	public static void main(String[] args) {
		int[] b = {3, 5, 10, 10, 10, 15, 15, 20};
		int[] a = {5, 10, 10, 15, 20};
		printIntersectionOfTwoSortedArrays(a, b);
	}
	
	public static void printIntersectionOfTwoSortedArrays(int[] a, int[] b) {
		int n1 = a.length, n2 = b.length, i = 0, j = 0;
		while(i < n1 && j < n2) {
			if(i > 0 && a[i] == a[i - 1]) {
				i++;
			} else if(a[i] > b[j]) {
				j++;
			} else if(a[i] < b[j]) {
				i++;
			} else {
				System.out.println(a[i]);
				i++;
				j++;
			}
		}
	}
	
}
