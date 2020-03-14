package sorting;

public class Problem76 {

	public static void main(String[] args) {
		int[] a = {3, 5, 10, 10, 10, 15, 15, 20};
		int[] b = {5, 10, 10, 15, 20};
		printUnionOfTwoSortedArrays(a, b);
	}

	public static void printUnionOfTwoSortedArrays(int[] a, int[] b) {
		int n1 = a.length, n2 = b.length, i = 0, j = 0;
		while(i < n1 && j < n2) {
			if(i > 0 && a[i] == a[i - 1]) {
				i++;
			} else if(j > 0 && b[j] == b[j - 1]) {
				j++;
			} else if(a[i] > b[j]) {
				System.out.println(b[j]);
				j++;
			} else if(a[i] < b[j]) {
				System.out.println(a[i]);
				i++;
			} else {
				System.out.println(a[i]);
				i++;
				j++;
			}
		}
		while(i < n1) {
			if(i == 0 || a[i] != a[i - 1]) {
				System.out.println(a[i]);
				i++;
			}
		}
		while(j < n2) {
			if(j == 0 || b[j] != b[j - 1]) {
				System.out.println(b[j]);
				j++;
			}
		}
	}
	
}
