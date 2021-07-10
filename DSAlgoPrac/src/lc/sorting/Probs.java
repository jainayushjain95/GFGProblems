package lc.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class TimestampWebsite {
	int timestamp;
	String website;
	public TimestampWebsite(int timestamp, String website) {
		super();
		this.timestamp = timestamp;
		this.website = website;
	}
}

class Pair {
	int left;
	int right;
	
	public Pair(int left, int right) {
		super();
		this.left = left;
		this.right = right;
	}
}

class CompareTimestampWebsite implements Comparator<TimestampWebsite> {

	@Override
	public int compare(TimestampWebsite o1, TimestampWebsite o2) {
		return o1.timestamp - o2.timestamp;
	}
}

class Triplet {
	int rowIndex;
	int columnIndex;
	int value;
	public Triplet(int rowIndex, int columnIndex, int value) {
		super();
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.value = value;
	}
}

class CompareTriplet implements Comparator<Triplet> {

	@Override
	public int compare(Triplet o1, Triplet o2) {
		// TODO Auto-generated method stub
		return o1.value - o2.value;
	}
	
}

public class Probs {
	
	public static void main(String[] args) {
		int[][] intervals = {{1,2},{3, 5}, {6, 7}, {8, 10}, {12, 16}};
		int[] newInterval = {4, 8};
		System.out.println(new Probs().insert(intervals, newInterval));
	}

	public int kthSmallest(int[][] matrix, int k) {
		PriorityQueue<Triplet> pq = new PriorityQueue<Triplet>(new CompareTriplet());
		for(int i = 0; i < matrix[0].length; i++) {
			pq.add(new Triplet(0, i, matrix[0][i]));
		}
		int sol = 0;
		
		while(k != 0) {
			k--;
			Triplet triplet = pq.poll();
			sol = triplet.value;
			if(triplet.rowIndex + 1 < matrix.length) {
				pq.add(new Triplet(triplet.rowIndex + 1, triplet.columnIndex, matrix[triplet.rowIndex + 1][triplet.columnIndex]));
			}
		}
		
		return sol;
	}
	
	public int[][] insert(int[][] intervals, int[] newInterval) {
		List<int[]> list = new LinkedList<int[]>();
		int index = 0;
		
		while(index < intervals.length && intervals[index][0] < newInterval[0]) {
			list.add(intervals[index++]);
		}
		
		list.add(newInterval);
		
		while(list.size() > 1 && list.get(list.size() - 2)[1] >= list.get(list.size() - 1)[0]) {
			int[] merged = new int[2];
			merged[0] = list.get(list.size() - 2)[0];
			merged[1] = Math.max(list.get(list.size() - 2)[1], list.get(list.size() - 1)[1]);
			list.set(list.size() - 2, merged);
			list.remove(list.size() - 1);
		}
		
		while(index < intervals.length) {
			list.add(intervals[index++]);
			if(list.size() > 1 && list.get(list.size() - 2)[1] >= list.get(list.size() - 1)[0]) {
				int[] merged = new int[2];
				merged[0] = list.get(list.size() - 2)[0];
				merged[1] = Math.max(list.get(list.size() - 2)[1], list.get(list.size() - 1)[1]);
				list.set(list.size() - 2, merged);
				list.remove(list.size() - 1);
			}
		}
		
		int[][] intervalsPlusOne = new int[list.size()][2];
		index = 0;
		for(int[] interval : list) {
			intervalsPlusOne[index][0] = interval[0];
			intervalsPlusOne[index++][1] = interval[1];
		}
		return intervalsPlusOne;
    }
	
	public int[][] highFive(int[][] items) {
		List<Integer> ids = new ArrayList<Integer>();
		Map<Integer, List<Integer>> map = prepareMap(items, ids);
		int[][] solve = new int[map.size()][2];
		int index = 0;
		
		for(Integer id : map.keySet()) {
			sortScores(map.get(id));
		}
		sortScores(ids);
		
		for(Integer id : ids) {
			List<Integer> scores = map.get(id);
			solve[index][0] = id;
			solve[index++][1] = getAverage(scores);
		}
		
		return solve;
    }
	
	public int getAverage(List<Integer> scores) {
		int avg = 0;
		for(int i = scores.size() - 1;i >= scores.size() - 5; i--) {
			avg += scores.get(i);
		}
		avg = avg / 5;
		return avg;
	}
	
	public void sortScores(List<Integer> scores) {
		quickSortScores(scores, 0, scores.size() - 1);
	}
	
	public void quickSortScores(List<Integer> scores, int beginIndex, int endIndex) {
		if(beginIndex < endIndex) {
			int partitionIndex = partition(scores, beginIndex, endIndex);
			quickSortScores(scores, beginIndex, partitionIndex - 1);
			quickSortScores(scores, partitionIndex + 1, endIndex);
		}
	}
	
	public int partition(List<Integer> scores, int beginIndex, int endIndex) {
		int pivotElement = scores.get(endIndex), partitionIndex = beginIndex;
		
		for(int i = beginIndex;i < endIndex; i++) {
			if(scores.get(i) <= pivotElement) {
				swap(scores, i, partitionIndex++);
			}
		}
		
		swap(scores, partitionIndex, endIndex);
		
		return partitionIndex;
	}
	
	public Map<Integer, List<Integer>> prepareMap(int[][] items, List<Integer> ids) {
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for(int i = 0;i < items.length; i++) {
			List<Integer> scores = new ArrayList<Integer>();
			if(map.containsKey(items[i][0])) {
				scores = map.get(items[i][0]);
				scores.add(items[i][1]);
			} else {
				ids.add(items[i][0]);
				scores.add(items[i][1]);
				map.put(items[i][0], scores);	
			}
		}
		return map;
	}
	
	public void swap(List<Integer> scores, int beginIndex, int endIndex) {
		int temp = scores.get(beginIndex);
		scores.set(beginIndex, scores.get(endIndex));
		scores.set(endIndex, temp);
	}
	
	
	
	
	
	
	
	public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
		List<String> threeSequenceList = new ArrayList<String>();
		Map<String, List<TimestampWebsite>> map = getMap(username, timestamp, website);
		String maxCountSequence = getSequenceCountMap(map);
		threeSequenceList = Arrays.asList(maxCountSequence.split(" "));
		
		return threeSequenceList;
    }
	
	public String getSequenceCountMap(Map<String, List<TimestampWebsite>> map) {
		String maxCountSequence = "";
		Map<String, Integer> sequenceCountMap = new HashMap<String, Integer>();
		for(String name : map.keySet()) {
			System.out.println("\n");
			List<TimestampWebsite> timestampWebsites = map.get(name);
			for(int i = 0;i < timestampWebsites.size(); i++) {
				System.out.println(name + ", " + timestampWebsites.get(i).timestamp + ", " + timestampWebsites.get(i).website);
			}
		}
		for(String name : map.keySet()) {
			List<TimestampWebsite> timestampWebsites = map.get(name);
			if(timestampWebsites.size() >= 3) {
				Set<String> visits = new HashSet<String>();
				for(int i = 0;i < timestampWebsites.size() - 2; i++) {
					for(int j = i + 1;j < timestampWebsites.size() - 1; j++) {
						for(int k = j + 1;k < timestampWebsites.size(); k++) {
							String threeSequence = getThreeSequence(timestampWebsites, i, j, k);
							if(!visits.contains(threeSequence)) {
								int freq = 1 + sequenceCountMap.getOrDefault(threeSequence, 0);
								sequenceCountMap.put(threeSequence, freq);	
								visits.add(threeSequence);
								if(maxCountSequence == "" || sequenceCountMap.get(threeSequence) > sequenceCountMap.get(maxCountSequence)) {
									maxCountSequence = threeSequence;
								} else if(sequenceCountMap.get(threeSequence) == sequenceCountMap.get(maxCountSequence)) {
									if(maxCountSequence.compareTo(threeSequence) > 0) {
										maxCountSequence = threeSequence;
									}
								}
							}
						}	
					}
				}
			}
		}
		
		return maxCountSequence;
	}
	
	public String getThreeSequence(List<TimestampWebsite> timestampWebsites, int i, int j, int k) {
		StringBuilder stringBuilder = new StringBuilder(timestampWebsites.get(i).website);
		stringBuilder.append(" ");
		stringBuilder.append(timestampWebsites.get(j).website);
		stringBuilder.append(" ");
		stringBuilder.append(timestampWebsites.get(k).website);
		return stringBuilder.toString();
	}
	
	public Map<String, List<TimestampWebsite>> getMap(String[] username, int[] timestamp, String[] website) {
		Map<String, List<TimestampWebsite>> map = new HashMap<String, List<TimestampWebsite>>();
		
		for(int i = 0;i < username.length; i++) {
			String name = username[i];
			if(!map.containsKey(name)) {
				map.put(name, new ArrayList<TimestampWebsite>());
			}
			map.get(name).add(new TimestampWebsite(timestamp[i], website[i]));
		}
		
		for(String name : map.keySet()) {
			Collections.sort(map.get(name), new CompareTimestampWebsite());
		}
		
		return map;
	}
	
}
