package sorting.gfg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Interval implements Comparable<Interval> {
	int left;
	int right;
	
	public Interval(int left, int right) {
		this.left = left;
		this.right = right;
	}
	
	public int compareTo(Interval o) {
		return this.left - o.left;
	}

	public String toString() {
		return "Interval [left=" + left + ", right=" + right + "]\n";
	}
	
}

public class SortingProblems {

	public static void main(String[] args) {
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(5, 10));
		intervals.add(new Interval(3, 15));
		intervals.add(new Interval(18, 30));
		intervals.add(new Interval(2, 7));
		mergeOverlappigIntervals(intervals);
	}

	public static void mergeOverlappigIntervals(List<Interval> intervals) {
		Collections.sort(intervals);
		int resultIndex = 0;
		
		for(int i = 1;i < intervals.size(); i++) {
			Interval a = intervals.get(resultIndex);
			Interval b = intervals.get(i);
			
			if(areOverlapping(a, b)) {
				a.left = Math.min(a.left, b.left);
				a.right = Math.max(a.right, b.right);
			} else {
				resultIndex++;
				intervals.get(resultIndex).left = b.left;
				intervals.get(resultIndex).right = b.right;
			}
		}
		for(int i = 0;i <= resultIndex; i++) {
			System.out.println(intervals.get(i).left + ", " + intervals.get(i).right);
		}
	}
	
	public static void bubbleSort(int[] a) {
		for(int i = a.length - 1;i > 0; i--) {
			for(int j = 0;j < i; j++) {
				if(a[j] > a[j + 1]) {
					swap(a, j, j + 1);
				}
			}
		}
	}
	
	public static void selectionSort(int[] a) {
		int i = 0, j = 0;
		for(i = 0;i < a.length - 1; i++) {
			int currMinIndex = i;
			for(j = i + 1;j < a.length; j++) {
				if(a[currMinIndex] > a[j]) {
					currMinIndex = j;
				}
			}
			swap(a, i, currMinIndex);
		}
	}
	
	public static void insertionSort(int[] a) {
		int i = 0, j = 0;
		for(i = 1;i < a.length; i++) {
			j = i - 1;
			while(j >= 0 && a[j] > a[j + 1]) {
				swap(a, j + 1, j);
				j--;
			}
		}
	}
	
	public static void mergeSort(int[] a, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
			return;
		}
		int midIndex = mid(beginIndex, endIndex);
		mergeSort(a, beginIndex, midIndex);
		mergeSort(a, midIndex + 1, endIndex);
		int[] mergedArray = merge(a, beginIndex, midIndex, endIndex);
		for(int i = 0;i < mergedArray.length; i++) {
			a[i + beginIndex] = mergedArray[i];
		}
	}
	
	public static void printIntersectionOfTwoSortedArrays(int[] a, int[] b) {
		int i = 0, j = 0;
		while(i < a.length && j < b.length) {
			while(i < a.length - 1 && a[i] == a[i + 1]) {
				i++;
			}
			while(j < b.length - 1 && b[j] == b[j + 1]) {
				j++;
			}
			if(i < a.length && j < b.length) {
				if(a[i] < b[j]) {
					i++;
				} else if(a[i] > b[j]) {
					j++;
				} else {
					System.out.println(a[i]);
					i++;
					j++;
				}	
			}
		}
	}
	
	public static int countInversionsInArray(int[] a, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
			return 0;
		}
		int result = 0;
		int midIndex = mid(beginIndex, endIndex);
		result += countInversionsInArray(a, beginIndex, midIndex);
		result += countInversionsInArray(a, midIndex + 1, endIndex);
		result += countAndMerge(a, beginIndex, midIndex, endIndex);
		return result;
	}
	
	
	public static void quickSort(int[] a, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
			return;
		}
		int p = Partition(a, beginIndex, endIndex);
		quickSort(a, beginIndex, p);
		quickSort(a, p + 1, endIndex);
	}
	
	public static int Partition(int[] a, int beginIndex, int endIndex) {
		int i = beginIndex, j = endIndex + 1;
		int pivot = a[i];
		
		while(i < j) {
			do {
				i++;
			} while(i < a.length && a[i] <= pivot);
			
			do {
				j--;
			} while(j >= 0 && a[j] > pivot);
			
			if(i < j)
				swap(a, i, j);
		}
		swap(a, beginIndex, j);
		return j;
	}
	
	
	public static int getKthLargest(int[] a, int k) {
		int kthLargestElement = -1;
		int beginIndex = 0, endIndex = a.length - 1;
		
		while(beginIndex <= endIndex) {
			int p = Partition(a, beginIndex, endIndex);
			if(a.length - p == k) {
				kthLargestElement = a[p];
				break; 
			} else if(p < a.length - k) {
				beginIndex = p + 1;
			} else {
				endIndex = p - 1;
			}
		}
		
		return kthLargestElement;
	}
	
	public static void sortBinaryArray(int[] a) {
		int beginIndex = -1, endIndex = a.length;
		
		while(beginIndex <= endIndex) {
			do {
				beginIndex++;
			} while(beginIndex < a.length && a[beginIndex] == 0);
			
			do {
				endIndex--;
			} while(endIndex >= 0 && a[endIndex] == 1);
			
			if(beginIndex <= endIndex && beginIndex < a.length && endIndex >= 0) {
				swap(a, beginIndex, endIndex);
			}
		}
	}
	
	public static void sortArrayWithThreeTypesOfElements(int[] a) {
		int beginIndex = 0, endIndex = a.length - 1, midIndex = 0;
		
		while(midIndex <= endIndex) {
			if(a[midIndex] == 0) {
				swap(a, beginIndex, midIndex);
				beginIndex++;
				midIndex++;
			} else if(a[midIndex] == 1) {
				midIndex++;
			} else {
				swap(a, midIndex, endIndex);
				endIndex--;
			}
		}
	}
	
	/*
	 *########################################################################################################
	 * 												Helpers
	 *########################################################################################################
	 */

	public static int countAndMerge(int[] a, int beginIndex, int midIndex, int endIndex) {
		int i = beginIndex, j = midIndex + 1;
		int[] mergedArray = new int[endIndex - beginIndex + 1];
		int index = 0;
		int count = 0;
		while(i <= midIndex && j <= endIndex) {
			if(a[i] <= a[j]) {
				mergedArray[index++] = a[i++];
			} else {
				count += midIndex + 1 - i;
				mergedArray[index++] = a[j++];
			}
		}
		
		while(i <= midIndex) {
			mergedArray[index++] = a[i++];
		}
		
		while(j <= endIndex) {
			mergedArray[index++] = a[j++];
		}
		
		for(i = 0;i < mergedArray.length; i++) {
			a[i + beginIndex] = mergedArray[i];
		}
		
		return count;
	}
	
	public static int[] merge(int[] a, int beginIndex, int midIndex, int endIndex) {
		int i = beginIndex, j = midIndex + 1;
		int[] mergedArray = new int[endIndex - beginIndex + 1];
		int index = 0;
		
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
		
		return mergedArray;
	}
	
	public static int mid(int i, int j) {
		return (j - i)/2 + i;
	}

	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void print(int[] a, int n) {
		for(int i = 0;i < n; i++) {
			System.out.println(a[i]);
		}
	}
	
	public static boolean areOverlapping(Interval a, Interval b) {
		boolean areOverlapping = false;
		
		int maxLeft = Math.max(a.left, b.left);
		areOverlapping = a.left <= maxLeft && a.right >= maxLeft && b.left <= maxLeft && b.right >= maxLeft;
		
		return areOverlapping;
	}
}
