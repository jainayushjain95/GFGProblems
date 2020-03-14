package sorting;

public class Problem77 {

	public static void main(String[] args) {
		int[] a = {2, 4, 1, 3, 5};
		System.out.println(countInversionsInArray(a, 0, a.length - 1));
	}

	public static int countInversionsInArray(int[] a, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
			return 0;
		}
		int midIndex = getMidIndex(beginIndex, endIndex);
		int res = 0;
		res += countInversionsInArray(a, beginIndex, midIndex);
		res += countInversionsInArray(a, 1 + midIndex, endIndex);
		res += countAndMerge(a, beginIndex, midIndex, endIndex);
		return res;
	}
	
	public static int countAndMerge(int[] a, int beginIndex, int midIndex, int endIndex) {
		int inversionsCount = 0;
		int i = beginIndex, j = midIndex + 1;
		int[] mergedArray = new int[endIndex - beginIndex + 1];
		int index = 0;
		while(i <= midIndex && j <= endIndex) {
			if(a[i] > a[j]) {
				mergedArray[index++] = a[j++];
				inversionsCount += midIndex - i + 1;
			} else {
				mergedArray[index++] = a[i++];
			}
		}
		
		while(i <= midIndex) {
			mergedArray[index++] = a[i++];
		}
		
		while(j <= endIndex) {
			mergedArray[index++] = a[j++];
		}
		for(int k = 0; k < mergedArray.length; k++) {
			a[k + beginIndex] = mergedArray[k];
		}
		return inversionsCount;
	}
	

	public static int getMidIndex(int beginIndex, int endIndex) {
		return (endIndex - beginIndex)/2 + beginIndex;
	}
	
}
