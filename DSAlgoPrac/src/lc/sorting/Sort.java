package lc.sorting;

public class Sort {

	public static void main(String[] args) {
		int[] nums = {7, 2, 1, 6, 8, 5, 3, 4};
		quickSort(nums, 0, nums.length - 1);
		for(int x : nums) {
			System.out.println(x);
		}
	}
	
	public static void selectionSort(int[] nums) {
		int i = 0, j = 0;
		for(i = 0;i < nums.length; i++) {
			int minIndex = i;
			for(j = i;j < nums.length; j++) {
				if(nums[minIndex] > nums[j]) {
					minIndex = j;
				}
			}
			swap(nums, i, minIndex);
		}
	}

	public static void bubbleSort(int[] nums) {
		for(int i = nums.length - 1;i > 0; i--) {
			boolean isSwapped = false;
			for(int j = 0;j < i; j++) {
				if(nums[j] > nums[j + 1]) {
					isSwapped = true;
					swap(nums, j, j + 1);
				}
			}
			if(!isSwapped) {
				break;
			}
		}
	}
	
	public static void insertionSort(int[] nums) {
		for(int i = 1;i < nums.length; i++) {
			int element = nums[i];
			int j = 0;
			for(j = i;j > 0 && element < nums[j - 1]; j--) {
				nums[j] = nums[j - 1];
			}
			nums[j] = element;
		}
	}
	
	public static void mergeSort(int[] nums, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex)
			return;
		int midIndex = getMidIndex(beginIndex, endIndex);
		mergeSort(nums, beginIndex, midIndex);
		mergeSort(nums, midIndex + 1, endIndex);
		int[] mergedArray = merge(nums, beginIndex, endIndex);
		for(int i = 0;i < mergedArray.length; i++) {
			nums[i + beginIndex] = mergedArray[i];
		}
	}
	
	public static int[] merge(int[] nums, int beginIndex, int endIndex) {
		int midIndex = getMidIndex(beginIndex, endIndex);
		int[] mergedArray = new int[endIndex - beginIndex + 1];
		int index = 0, i = beginIndex, j = midIndex + 1;
		while(i <= midIndex && j <= endIndex) {
			if(nums[i] < nums[j]) {
				mergedArray[index++] = nums[i++];
			} else if(nums[j] < nums[i]) {
				mergedArray[index++] = nums[j++];
			} else {
				mergedArray[index++] = nums[i++];
				mergedArray[index++] = nums[j++];
			}
		}
		
		while(i <= midIndex) {
			mergedArray[index++] = nums[i++];
		}
		
		while(j <= endIndex) {
			mergedArray[index++] = nums[j++];
		}
		
		return mergedArray;
	}
	
	public static void quickSort(int[] nums, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
			return;
		}
		int partionIndex = partitionRandom(nums, beginIndex, endIndex);
		quickSort(nums, beginIndex, partionIndex - 1);
		quickSort(nums, partionIndex + 1, endIndex);
	}
	
	public static int partitionRandom(int[] nums, int beginIndex, int endIndex) {
		int partitionIndex = (int)(beginIndex + Math.random()*(endIndex));
		swap(nums, partitionIndex, endIndex);
		return partition(nums, beginIndex, endIndex);
	}
	
	public static int partition(int[] nums, int beginIndex, int endIndex) {
		int partitionIndex = beginIndex;
		int pivotElement = nums[endIndex];
		
		for(int i = beginIndex; i < endIndex; i++) {
			if(nums[i] <= pivotElement) {
				swap(nums, partitionIndex++, i);
			}
		} 
		swap(nums, partitionIndex, endIndex);
		
		return partitionIndex;
	}
	
	public static void swap(int[] nums, int beginIndex, int endIndex) {
		int temp = nums[beginIndex];
		nums[beginIndex] = nums[endIndex];
		nums[endIndex] = temp;
	}
	
	public static int getMidIndex(int beginIndex, int endIndex) {
		return ((endIndex - beginIndex) >> 1) + beginIndex;
	}
}
