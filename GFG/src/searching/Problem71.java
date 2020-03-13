package searching;

public class Problem71 {

	public static void main(String[] args) {
		int[] a = {30, 40, 50, 60};
		int[] b = {5, 6, 7, 8, 9};
		System.out.println(medianOfTwoSortedArrays(a, b, a.length, b.length, 0, a.length));

	}
	
	public static double medianOfTwoSortedArrays(int[] a, int[] b, int n1, int n2, int start, int end) {
		if(start > end) {
			return 0;
		}
		int i1 = (start + end) / 2;
		int i2 = (n1 + n2 + 1)/2 - i1;
		
		int min1 = (i1 == n1) ? Integer.MAX_VALUE : a[i1];
		int max1 = (i1 == 0) ? Integer.MIN_VALUE : a[i1 - 1];
		int min2 = (i2 == n2) ? Integer.MAX_VALUE : b[i2];;
		int max2 = (i2 == 0) ? Integer.MIN_VALUE : b[i2 - 1];
		
		if(max1 <= min2 && max2 <= min1) {
			if((n1 + n2) % 2 != 0) {
				return (double) Math.max(max1, max2);
			} else {
				return (double) (Math.max(max1, max2) + Math.min(min1, min2))/2;
			}
		}
		
		else if(max1 > min2) {
			end = i1 - 1;
		} else {
			start = i1 + 1;
		}
		
		return medianOfTwoSortedArrays(a, b, n1, n2, start, end);
	}

}
