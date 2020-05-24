package binarysearch.geeksforgeeks;

public class FindAPeakElementInUnsortedArray {

	public static void main(String[] args) {
		int[] a = {10, 20, 15, 5, 23, 90, 67};
		System.out.println(getOnePeakElement(a, 0, a.length - 1));
	}

	public static int getOnePeakElement(int[] a, int beginIndex, int endIndex) {
		if(beginIndex > endIndex) {
			return -1;
		}
		int midIndex = getMidIndex(beginIndex, endIndex);
		if(isPeakElement(a, midIndex)) {
			return midIndex;
		}
		if(a[midIndex] < a[midIndex + 1]) {
			return getOnePeakElement(a, midIndex + 1, endIndex);
		}
		return getOnePeakElement(a, beginIndex, midIndex + 1);
	}
	
	public static boolean isPeakElement(int[] a, int index) {
		boolean isPeak = false;
		if(index == 0 && a[index] >= a[index + 1]) {
			isPeak = true;
		} else if(index == a.length - 1 && a[index - 1] <= a[index]) {
			isPeak = true;
		}
		if(index != 0 && index != a.length - 1) {
			isPeak = a[index] >= a[index + 1] && a[index] >= a[index - 1];
		}
		return isPeak;
	}
	
	public static int getMidIndex(int i, int j) {
		return i + (j - i)/2;
	}

}
