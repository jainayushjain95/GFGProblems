package lc.sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeastNumberofUniqueIntegersafterKRemovals {

	public static void main(String[] args) {
		int[] arr = {13,22,100,22,5,62,13,24,81,15,99,14,20,2,61,10,40,47,33,7,38,47,92,31,15,40,73,48,24,55,81,63,37,23,59,78,5,50,10,51,67,9,18,78,89,40,71,7,32,67,6,34,69,59,19,39,96,64,81,96,64,5,82,59,29,93,42,72,38,60,82,40,97,91,4,22,85,80,33,51,10,21,54,91,2,94,38,38,19,75,37,7,76,7,27,8,76,11,25,5,13,22,100,22,5,62,13,24,81,15,99,14,20,2,61,10,40,47,33,7,38,47,92,31,15,40,73,48,24,55,81,63,37,23,59,78,5,50,10,51,67,9,18,78,89,40,71,7,32,67,6,34,69,59,19,39,96,64,81,96,64,5,82,59,29,93,42,72,38,60,82,40,97,91,4,22,85,80,33,51,10,21,54,91,2,94,38,38,19,75,37,7,76,7,27,8,76,11,25,5};
		long a = System.currentTimeMillis() * 1000;
		findLeastNumOfUniqueIntsSolve(arr, 78);
		long b = System.currentTimeMillis() * 1000;
		System.out.println(b-a);
	}

	public static int findLeastNumOfUniqueIntsSolve(int[] arr, int k) {
		Map<Integer, Integer> frequencyMap = calcFreq(arr);
		int[] uniques = getUniquesArray(frequencyMap);
		Arrays.parallelSort(uniques);
		
		int i = 0;
		for(i = 0;i < uniques.length;i++) {
			k = k - uniques[i];
			if(k < 0) {
				break;
			}
		}
		
		return uniques.length - i; 
	}
	
	public static int[] getUniquesArray(Map<Integer, Integer> frequencyMap) {
		int[] uniques = new int[frequencyMap.size()];
		int index = 0;
		for(int x : frequencyMap.keySet()) {
			uniques[index++] = frequencyMap.get(x);
		}
		return uniques;
	}
	
	public static void quickSort(int[] arr, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
			return;
		}
		int partitionIndex = partition(arr, beginIndex, endIndex);
		quickSort(arr, beginIndex, partitionIndex - 1);
		quickSort(arr, partitionIndex + 1, endIndex);
	}
	
	public static int partition(int[] arr, int beginIndex, int endIndex) {
		int partitionIndex = beginIndex;
		int pivotElement = arr[endIndex];
		for(int i = beginIndex;i < endIndex; i++) {
			if(pivotElement > arr[i]) {
				swap(arr, partitionIndex++, i);
			}
		}
		swap(arr, partitionIndex, endIndex);
		return partitionIndex;
	}

	public static void swap(int[] array, int beginIndex, int endIndex) {
		int temp = array[beginIndex];
		array[beginIndex] = array[endIndex];
		array[endIndex] = temp;
	}
	
	
	public static Map<Integer, Integer> calcFreq(int[] arr) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int x : arr) {
			if(map.containsKey(x)) {
				map.put(x, map.get(x) + 1);
			} else {
				map.put(x, 1);
			}
		}
		return map;
	}
}
