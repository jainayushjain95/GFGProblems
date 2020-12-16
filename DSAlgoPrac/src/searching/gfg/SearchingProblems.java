package searching.gfg;

public class SearchingProblems {

	public static void main(String[] args) {
		int[] a = {1, 10, 10, 10, 20, 20, 40};
		int element = 40;
		System.out.println(indexOfFirstOccurence(a, element, 0, a.length - 1));
	}

	public static int indexOfFirstOccurence(int[] a, int element, int beginIndex, int endIndex) {
		if(beginIndex > endIndex) {
			return -1;
		}
		int midIndex = getMid(beginIndex, endIndex);
		if(a[midIndex] > element) {
			return indexOfFirstOccurence(a, element, beginIndex, midIndex - 1);
		} else if(a[midIndex] < element) {
			return indexOfFirstOccurence(a, element, midIndex + 1, endIndex);
		} else if(midIndex == 0 || a[midIndex] != a[midIndex - 1]) {
			return midIndex;
		}
		return indexOfFirstOccurence(a, element, beginIndex, midIndex - 1);
	}
	
	/*
	 *########################################################################################################
	 * 												Helpers
	 *########################################################################################################
	 */
	public static int getMid(int beginIndex, int endIndex) {
		return (endIndex - beginIndex)/2 + beginIndex;
	}
}
