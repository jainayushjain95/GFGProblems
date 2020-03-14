package sorting;

public class Problem74 {

	public static void main(String[] args) {
		int[] a = {10, 5, 30, 15, 7};
		mergeSort(a, 0, a.length - 1);
		
	}

	public static void mergeSort(int[] a, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
			return;
		} 
		int midIndex = getMidIndex(beginIndex, endIndex);
		mergeSort(a, beginIndex, midIndex);
		mergeSort(a, midIndex + 1, endIndex);
		merge(a, beginIndex, midIndex, endIndex);
	}

	public static void merge(int[] a, int beginIndex, int midIndex, int endIndex) {
		int i = beginIndex, j = midIndex + 1;
		int index = 0;
		int[] mergedArray = new int[endIndex - beginIndex + 1];
		while(i <= midIndex && j <= endIndex) {
			if(a[i] < a[j]) {
				mergedArray[index++] = a[i++];
			} else {
				mergedArray[index++] = a[j++];
			}
		}
		while(i <= midIndex) {
			mergedArray[index++] = a[i++];
		}
		while(j <= endIndex) {
			mergedArray[index++] = a[j++];
		}
		for(int k = 0;k < mergedArray.length; k++) {
			a[beginIndex + k] = mergedArray[k];
		}
	}

	public static int getMidIndex(int beginIndex, int endIndex) {
		return (endIndex - beginIndex)/2 + beginIndex;
	}

}
