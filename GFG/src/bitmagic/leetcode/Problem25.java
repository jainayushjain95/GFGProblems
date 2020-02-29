package bitmagic.leetcode;

import java.util.HashMap;

public class Problem25 {

	public static void main(String[] args) {
		int[] input = {0,1,2,3,4,5,6,7,8};
		input = sortBySetBitsCount(input);
		for(int x : input) {
			System.out.println(x);
		}
	}

	public static int[] sortBySetBitsCount(int[] input) {
		HashMap<Integer, Integer> countSetBitsMap = getCountSetBitsCountMap(input);
		mergeSort(input, countSetBitsMap, 0, input.length-1);

		return input;
	}

	public static void mergeSort(int[] input, HashMap<Integer, Integer> countSetBitsMap, int beginIndex, int endIndex) {
		if(beginIndex < endIndex) {
			int mid = (endIndex - beginIndex)/2 + beginIndex;
			mergeSort(input, countSetBitsMap, beginIndex, mid);
			mergeSort(input, countSetBitsMap, mid + 1, endIndex);
			int[] mergedArray = merge(input, countSetBitsMap, beginIndex, endIndex);
			for(int i = 0; i < mergedArray.length; i++) {
				input[i + beginIndex] = mergedArray[i]; 
			}
		}
	}

	public static int[] merge(int[] input, HashMap<Integer, Integer> countSetBitsMap, int beginIndex, int endIndex) {
		int[] output = new int[endIndex - beginIndex + 1];
		int i = beginIndex, mid = (endIndex - beginIndex)/2 + beginIndex;
		int j = mid + 1, index = 0;
		while(i <= mid && j <= endIndex) {
			if(countSetBitsMap.get(input[i]) < countSetBitsMap.get(input[j])) {
				output[index] = input[i];
				index++;
				i++;
			} else if(countSetBitsMap.get(input[i]) > countSetBitsMap.get(input[j])) {
				output[index] = input[j];
				index++;
				j++;
			} else if(input[i] < input[j]) {
				output[index] = input[i];
				index++;
				i++;
			} else {
				output[index] = input[j];
				index++;
				j++;
			}
		}
		while(i <= mid) {
			output[index] = input[i];
			index++;
			i++;
		}
		while(j <= endIndex) {
			output[index] = input[j];
			index++;
			j++;
		}
		return output;
	}

	public static HashMap<Integer, Integer> getCountSetBitsCountMap(int[] input) {
		HashMap<Integer, Integer> countSetBitsMap = new HashMap<Integer, Integer>();
		for(int x : input) {
			countSetBitsMap.put(x, getNoOfSetBit(x));
		}
		return countSetBitsMap;
	}

	public static int getNoOfSetBit(int n) {
		int setBitsCount = 0;
		while(n > 0) {
			setBitsCount++;
			n = turnOffLastSetBit(n);
		}
		return setBitsCount;
	}

	public static int turnOffLastSetBit(int n) {
		return n & (n-1);
	}

}
