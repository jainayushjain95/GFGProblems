package searching;

public class Problem64 {

	public static void main(String[] args) {
		int[] a = {0, 0, 0, 1};
		System.out.println(a.length - getLeftMostOccurenceIndexOfOne(a, 0, a.length - 1));
	}
	
	public static int getLeftMostOccurenceIndexOfOne(int[] a, int beginIndex, int endIndex) {
		if(beginIndex > endIndex) {
			return -1;
		}
		int mid = (endIndex - beginIndex)/2 + beginIndex;
		if(a[mid] == 1 && (mid == 0 || a[mid - 1] == 0)) {
			return mid;
		}
		if(a[mid] == 0) {
			return getLeftMostOccurenceIndexOfOne(a, mid + 1, endIndex);
		} else {
			return getLeftMostOccurenceIndexOfOne(a, beginIndex, mid - 1);
		}
	}

}
