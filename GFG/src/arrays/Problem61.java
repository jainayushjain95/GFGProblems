package arrays;

public class Problem61 {

	public static void main(String[] args) {
		int[] a = {8, -8, 9, -9, 10, -11, 12};
		System.out.println(minAdjDiff(a));
	}
	
	public static int minAdjDiff(int[] a) {
		int minDiff = Math.abs(a[0] - a[a.length - 1]);
		for(int i = 0;i < a.length - 1; i++) {
			int currDiff = Math.abs(a[i] - a[i + 1]);
			minDiff = Math.min(minDiff, currDiff);
		}
		return minDiff;
	}

}
