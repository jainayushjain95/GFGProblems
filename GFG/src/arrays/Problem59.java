package arrays;

public class Problem59 {

	public static void main(String[] args) {
		int[] a = {10, 10, 10, 10, 1};
		maxAndSecondMax(a);
	}
	
	public static void maxAndSecondMax(int[] a) {
		int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
		for(int x : a) {
			if(x > max1) {
				max2 = max1;
				max1 = x;
			} else if(x > max2 && x < max1) {
				max2 = x;
			}
		}
		if(max2 == Integer.MIN_VALUE) {
		    max2 = -1;
		}
		System.out.println(max1 + " " + max2);
	}
}
