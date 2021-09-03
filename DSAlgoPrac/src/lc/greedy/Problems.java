package lc.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Compare implements Comparator<int[]> {

	public int compare(int[] o1, int[] o2) {
		return o1[0] - o2[0];
	}

}

public class Problems {
	public static void main(String[] args) {
		int[] nums1 = {5, 3, 4, 2};
		int[] nums2 = {4, 2, 2, 5};
		System.out.println((new Problems()).minProductSum(nums1, nums2));
	}

	public int minProductSum(int[] nums1, int[] nums2) {
		String[] a = {"1", "@"};
		List<String> as = Arrays.asList(a);
		int[] freq1 = new int[101];
		int[] freq2 = new int[101];

		for(int i = 0;i < nums1.length; i++) {
			freq1[nums1[i]]++;
			freq2[nums2[i]]++;
		}

		int minSum = 0, index1 = 1, index2 = 100;

		while(index1 <= 100) {
			if(freq1[index1] == 0) {
				index1++;
			} else if(freq2[index2] == 0) {
				index2--;
			} else {
				int minFreq = Math.min(freq1[index1], freq2[index2]);
				minSum += minFreq * (index1 * index2);
				freq1[index1] -= minFreq;
				freq2[index2] -= minFreq;
			}
		}

		return minSum;
	}
	public int balancedStringSplit(String s) {
		int count = 0, balance = 0;

		for(int i = 0;i < s.length(); i++) {
			char ch = s.charAt(i);
			if(ch == 'R') {
				balance++;
			} else {
				balance--;
			}
			if(balance == 0) {
				count++;
			}
		}

		return count;
	}

	public int countGoodRectangles(int[][] rectangles) {
		int maxLen = 0, solve = 0;

		for(int i = 0;i < rectangles.length; i++) {
			int min = Math.min(rectangles[i][0], rectangles[i][1]);
			if(maxLen < min) {
				solve = 1;
				maxLen = min;
			} else if(maxLen == min) {
				solve++;
			}
		}
		int gold = 1;

		return solve;
	}

	public int minMeetingRooms(int[][] intervals) {
		int minCount = 1;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		Arrays.sort(intervals, new Compare());
		int activeIntervalIndex = 0;
		pq.add(intervals[activeIntervalIndex][1]);
		for(int i = 1;i < intervals.length; i++) {
			if(intervals[i][0] >= intervals[activeIntervalIndex][1]) {
				activeIntervalIndex = i;
				pq.poll();
				pq.add(intervals[i][1]);
			} else if(intervals[i][1] > intervals[activeIntervalIndex][1]) {
				int lastEndTime = pq.peek();
				if(intervals[i][0] < lastEndTime) {
					minCount++;
					pq.add(intervals[i][1]);
				} else {
					pq.poll();
					pq.add(intervals[i][1]);
				}
			} else {
				int lastEndTime = pq.peek();
				if(intervals[i][0] < lastEndTime) {
					minCount++;
					pq.add(intervals[i][1]);
				} else {
					pq.poll();
					pq.add(intervals[i][1]);
				}
				activeIntervalIndex = i;
			}
		}

		return minCount;
	}

	public boolean canAttendMeetings(int[][] intervals) {
		boolean canAttend = true;
		mergeSortIntervalsByStartTime(intervals, 0, intervals.length - 1);
		for(int i = 1; i < intervals.length; i++) {
			if(!(intervals[i][0] >= intervals[i - 1][1])) {
				canAttend = false;
				break;
			}
		}
		return canAttend;
	}

	public int eraseOverlapIntervals(int[][] intervals) {
		mergeSortIntervalsByStartTime(intervals, 0, intervals.length - 1);
		int count = 0;
		int activeIntervalIndex = 0;

		for(int i = 1; i < intervals.length; i++) {
			if(intervals[i][0] >= intervals[activeIntervalIndex][1]) {
				activeIntervalIndex = i;
			} else if(intervals[i][1] <= intervals[activeIntervalIndex][1]){
				count++;
				activeIntervalIndex = i;
			} else {
				count++;
			}
		}
		return count;
	}

	public void mergeSortIntervalsByStartTime(int[][] intervals, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
			return;
		}
		int midIndex = getMidIndex(beginIndex, endIndex);
		mergeSortIntervalsByStartTime(intervals, beginIndex, midIndex);
		mergeSortIntervalsByStartTime(intervals, midIndex + 1, endIndex);
		int[][] merged = mergeIntervalsByStartTime(intervals, beginIndex, endIndex, midIndex);
		for(int i = 0;i < merged.length; i++) {
			intervals[i + beginIndex][0] = merged[i][0];
			intervals[i + beginIndex][1] = merged[i][1];
		}
	}

	public static int[][] mergeIntervalsByStartTime(int[][] intervals, int beginIndex, int endIndex, int midIndex) {
		int[][] merged = new int[endIndex - beginIndex + 1][2];
		int i = beginIndex, j = midIndex + 1, index = 0;

		while(i <= midIndex && j <= endIndex) {
			if(intervals[i][0] <= intervals[j][0]) {
				merged[index][0] = intervals[i][0];
				merged[index++][1] = intervals[i++][1];
			} else {
				merged[index][0] = intervals[j][0];
				merged[index++][1] = intervals[j++][1];
			}
		}

		while(i <= midIndex) {
			merged[index][0] = intervals[i][0];
			merged[index++][1] = intervals[i++][1];
		}

		while(j <= endIndex) {
			merged[index][0] = intervals[j][0];
			merged[index++][1] = intervals[j++][1];
		}

		return merged;
	}

	public static int getMidIndex(int beginIndex, int endIndex) {
		return ((endIndex - beginIndex) >> 1) + beginIndex;
	}

}
