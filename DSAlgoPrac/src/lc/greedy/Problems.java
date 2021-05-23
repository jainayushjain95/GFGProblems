package lc.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Compare implements Comparator<int[]> {

	public int compare(int[] o1, int[] o2) {
		return o1[0] - o2[0];
	}
	
}

public class Problems {
	public static void main(String[] args) {
		int[][] intervals = {
				{1, 5}, {8, 9}, {8, 9}
		};
		System.out.println((new Problems()).minMeetingRooms(intervals));
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
