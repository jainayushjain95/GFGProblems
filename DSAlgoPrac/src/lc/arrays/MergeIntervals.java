package lc.arrays;

import java.util.LinkedList;

public class MergeIntervals {

	public static void main(String[] args) {
		int[][] intervals = {
				{1, 3}, {2, 6}, {8, 10}, {15, 16}, 
		};
		int[][] data = mergeOverlappingIntervals(intervals);
		for(int i = 0;i < data.length; i++) {
			System.out.println(data[i][0] + ", " + data[i][1]);
		}
	}

	public static int[][] mergeOverlappingIntervals(int[][] intervals) {
		if(intervals.length <= 1) {
			return intervals;
		}
		mergeSortIntervals(intervals, 0, intervals.length - 1);
		LinkedList<int[]> mergedArray = new LinkedList<int[]>();

		for(int[] interval : intervals) {
			if(mergedArray.size() == 0 || mergedArray.getLast()[1] < interval[0]) {
				mergedArray.add(interval);
			} else {
				mergedArray.getLast()[1] = Math.max(mergedArray.getLast()[1], interval[1]);
			}
		}

		return mergedArray.toArray(new int[mergedArray.size()][2]);
	}

	public static boolean areOverlapping(int left1, int right1, int left2, int right2) {
		int minRight = Math.min(right1, right2);
		return left1 <= minRight && left2 <= minRight && right1 >= minRight && right2 >= minRight;
	}

	public static void mergeSortIntervals(int[][] intervals, int left, int right) {
		if(left >= right) {
			return;
		}
		int midIndex = getMidIndex(left, right);
		mergeSortIntervals(intervals, left, midIndex);
		mergeSortIntervals(intervals, midIndex + 1, right);
		int[][] merged = mergeIntervals(intervals, left, right);

		for(int i = 0;i < merged.length; i++) {
			intervals[left + i][0] = merged[i][0];
			intervals[left + i][1] = merged[i][1];
		}
	}

	public static int[][] mergeIntervals(int[][] intervals, int left, int right) {
		int[][] merged = new int[right - left + 1][2];
		int midIndex = getMidIndex(left, right);
		int i = left, j = 1 + midIndex;
		int index = 0;

		while(i <= midIndex && j <= right) {
			if(intervals[i][0] <= intervals[j][0]) {
				merged[index][0] = intervals[i][0];
				merged[index][1] = intervals[i][1];
				index++;
				i++;
			} else {
				merged[index][0] = intervals[j][0];
				merged[index][1] = intervals[j][1];
				index++;
				j++;
			}
		}

		while(i <= midIndex) {
			merged[index][0] = intervals[i][0];
			merged[index][1] = intervals[i][1];
			index++;
			i++;
		}

		while(j <= right) {
			merged[index][0] = intervals[j][0];
			merged[index][1] = intervals[j][1];
			index++;
			j++;
		}

		return merged;
	}

	public static int getMidIndex(int left, int right) {
		return (right - left)/2 + left;
	}

}
