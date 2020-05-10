package arrays.geeksforgeeks;

public class TrappingRainWater {

	public static void main(String[] args) {
		int[] a = {5, 0, 6, 2, 3};
		int[] lPeaks = getLeftPeaks(a);
		int[] rPeaks = getRightPeaks(a);
		
		int amount = 0;
		for(int i = 1;i < a.length - 1; i++) {
			amount += Math.min(lPeaks[i], rPeaks[i]) - a[i];
		}
		System.out.println(amount);
		
	}
	
	public static int[] getLeftPeaks(int[] a) {
		int[] leftPeaks = new int[a.length];
		leftPeaks[0] = a[0];
		for(int i = 1;i < a.length; i++) {
			leftPeaks[i] = Math.max(a[i], leftPeaks[i-1]);
		}
		return leftPeaks;
	}
	
	public static int[] getRightPeaks(int[] a) {
		int[] rightPeaks = new int[a.length];
		rightPeaks[a.length - 1] = a[a.length - 1];
		for(int i = a.length - 2;i >= 0; i--) {
			rightPeaks[i] = Math.max(a[i], rightPeaks[i + 1]);
		}
		return rightPeaks;
	}

}
