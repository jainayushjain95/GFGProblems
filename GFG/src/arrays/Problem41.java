package arrays;

public class Problem41 {

	public static void main(String[] args) {
		int[] a = {5, 0, 6, 2, 3};
		System.out.println(trappingRainWater(a));
	}
	
	public static int trappingRainWater(int[] a) {
		int maxVolume = 0;
		int n = a.length;
		int[] lMax = getLeftMax(a);
		int[] rmax = getRightMax(a);
		
		
		for(int i = 1; i < n - 1; i++) {
			maxVolume += Math.min(lMax[i], rmax[i]) - a[i];
		}
		
		return maxVolume;
	}
	
	public static int[] getLeftMax(int[] input) {
		int[] lMax = new int[input.length];
		lMax[0] = input[0];
		for(int i = 1; i < input.length; i++) {
			lMax[i] = Math.max(input[i], lMax[i - 1]);
		}
		return lMax;
	}
	
	public static int[] getRightMax(int[] input) {
		int[] rMax = new int[input.length];
		rMax[input.length - 1] = input[input.length - 1];
		for(int i = input.length - 2; i >= 0; i--) {
			rMax[i] = Math.max(input[i], rMax[i + 1]);
		}
		return rMax;
	}

}
