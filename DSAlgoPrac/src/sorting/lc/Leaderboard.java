package sorting.lc;

import java.util.HashMap;
import java.util.Map;

public class Leaderboard {

	Map<Integer, Integer> map;
	int[] sorted;
	boolean isSorted;
	
	public Leaderboard() {
		map = new HashMap<Integer, Integer>();
		isSorted = false;
	}

	public void addScore(int playerId, int score) {
		isSorted = false;
		if(map.containsKey(playerId)) {
			map.put(playerId, map.get(playerId) + score);
		} else {
			map.put(playerId, score);
		}
	}

	public int top(int K) {
		if(!isSorted) {
			sorted = sortMap(map);	
		}
		int sumOfTop = 0;
		for(int i = 0;i < K; i++) {
			sumOfTop += sorted[sorted.length - i - 1];
		}
		isSorted = true;
		return sumOfTop;
	}

	public void reset(int playerId) {
		isSorted = false;
		map.remove(playerId);
	}
    
	public int[] sortMap(Map<Integer, Integer> map) {
		int[] sortedArray = new int[map.size()];
		int index = 0;
		for(int x : map.keySet()) {
			sortedArray[index++] = map.get(x);
		}
		quickSort(sortedArray, 0, map.size() - 1);
		return sortedArray;
	}
	
	public void quickSort(int[] array, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
			return;
		}
		int partitionIndex = partition(array, beginIndex, endIndex);
		quickSort(array, beginIndex, partitionIndex - 1);
		quickSort(array, partitionIndex + 1, endIndex);
	}
	
	public int partition(int[] array, int beginIndex, int endIndex) {
		int partitionIndex = beginIndex;
		int pivotElement = array[endIndex];
		
		for(int i = beginIndex; i < endIndex; i++) {
			if(array[i] <= pivotElement) {
				swap(array, i, partitionIndex++);
			}
		}
		
		swap(array, partitionIndex, endIndex);
		
		return partitionIndex;
	}

	
	public void swap(int[] array, int beginIndex, int endIndex) {
		int temp = array[beginIndex];
		array[beginIndex] = array[endIndex];
		array[endIndex] = temp;
	}
	
	
	public static void main(String[] args) {
		Leaderboard leaderboard = new Leaderboard();
		leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
		leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
		leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
		leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
		leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
		System.out.println(leaderboard.top(1));           // returns 73;
		leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
		leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
		leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
		System.out.println(leaderboard.top(3));
	}
}
