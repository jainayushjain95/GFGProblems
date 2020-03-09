package searching;

public class Problem62 {

	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
		System.out.println(leftMostOccurence(a, 0, a.length-1, 3));
	}
	
	public static int leftMostOccurence(int[] a, int beginIndex, int endIndex, int element) {
		if(beginIndex > endIndex) {
			return -1;
		}
		int mid = (endIndex - beginIndex)/2 + beginIndex;
		
		if(a[mid] == element && (mid == 0 || a[mid - 1] != element)) {
			return mid;
		}
		
		if(a[mid] < element) {
			return leftMostOccurence(a, mid + 1, endIndex, element);
		} else {
			return leftMostOccurence(a, beginIndex, mid - 1, element);
		} 
	}

}
