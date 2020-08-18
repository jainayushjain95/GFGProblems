package arrays;

import java.util.*;

public class AvoidFloodInTheCity {

	public static void main(String[] args) {
		int[] rains = {1,2,0,0,2,1};
		int[] avoid = avoidFloodSolve(rains);
		for(int x : avoid) {
			System.out.println(x);
		}
	}


	public static int getFirstZeroIndex(ArrayList<Integer> zeroes, int leftRange, int beginIndex, int endIndex) {
		int index = -1;
		if(endIndex > beginIndex) {
			index = -1;
			return index;
		}
		int midIndex = (endIndex - beginIndex)/2 + beginIndex;
		if(zeroes.get(midIndex) > leftRange) {
			index = midIndex;
			endIndex = midIndex - 1;
		}  else {
			beginIndex = midIndex + 1;
		}
		return index;
	}
	
	public static int[] avoidFloodSolve(int[] rains) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		boolean flag = true;
		ArrayList<Integer> zeroes = new ArrayList<Integer>();
		int[] avoid = new int[rains.length];
		for(int i = 0;i < rains.length; i++) {
			if(rains[i] != 0) {
				avoid[i] = -1;
				if(hm.containsKey(rains[i])) {
					int index = hm.remove(rains[i]);
					int firstZeroIndex = -1;
					
					
					int beginIndex = 0, endIndex = zeroes.size() - 1;
					while(beginIndex <= endIndex) {
						int midIndex = (endIndex - beginIndex)/2 + beginIndex;
						if(zeroes.get(midIndex) > index) {
							firstZeroIndex = midIndex;
							endIndex = midIndex - 1;
						}  else {
							beginIndex = midIndex + 1;
						}
					}
					
					
					if(firstZeroIndex == -1) {
						flag = false;
						break;
					} else {
						rains[zeroes.get(firstZeroIndex)] = -1;
						avoid[zeroes.get(firstZeroIndex)] = rains[i];
						zeroes.remove(zeroes.get(firstZeroIndex));
					}
					hm.put(rains[i], i);
				} else {
					avoid[i] = -1;
					hm.put(rains[i], i);
				}
			} else {
				zeroes.add(i);
			}
		}
		int[] output = {};
		if(flag) {
			if(!zeroes.isEmpty()) {
				for(int i : zeroes) {
					avoid[i] = 1;
				}
			}
		}
		return (flag) ? avoid : output;
	}
}
