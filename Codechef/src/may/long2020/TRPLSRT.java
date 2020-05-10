package may.long2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TRPLSRT {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			int[] a = new int[N + 1];
			int count = 0;
			for(int i = 1;i <= N; i++) {
				a[i] = sc.nextInt();
				if(i != a[i]) {
					count++;
				}
			}
			if(checkForCount(count)) {
				solve(a, N, K, count);
			}
			t--;
		}
	}

	public static void solve(int[] a, int n, int k, int count) {
		int steps = 0;
		List<Integer> list = new ArrayList<Integer>();
		boolean isSorted = true; 
		Map<Integer, List<Integer>> cycles = getCycles(a);
		List<List<Integer>> twoSizedCycles = new ArrayList<List<Integer>>();

		for(Map.Entry<Integer, List<Integer>> entry : cycles.entrySet()) {
			int currentIndex = entry.getKey();
			List<Integer> currentCycle = entry.getValue();
			
			if(currentCycle.size() > 2) {
				if(currentCycle.size() == 3) {
					count = count - 3;
				}
				int i = 1;
				for(; (i + 1) < currentCycle.size(); i = i + 2) {
					steps++;
					count = count - 2;
					updateOutputList(currentIndex, currentCycle.get(i), currentCycle.get(i + 1), list);
				}
				if(i == currentCycle.size() - 1) {
					List<Integer> curr = new ArrayList<Integer>();
					curr.add(currentIndex);
					curr.add(currentCycle.get(i));
					twoSizedCycles.add(curr);
				}
			} else {
				twoSizedCycles.add(currentCycle);
			}
		}

		if(twoSizedCycles.size() % 2 == 0) {
			steps = steps + twoSizedCycles.size();
			if(steps <= k) {
				for(int i = 0; i < twoSizedCycles.size(); i = i + 2) {
					List<Integer> cycleOne = twoSizedCycles.get(i);
					List<Integer> cycleTwo = twoSizedCycles.get(i + 1);
					list.add(cycleOne.get(0));
					list.add(cycleOne.get(1));
					list.add(cycleTwo.get(0));

					list.add(cycleOne.get(0));
					list.add(cycleTwo.get(1));
					list.add(cycleTwo.get(0));
				}
			}
		} else {
			steps = k + 1;
		}

		if(steps > k) {
			isSorted = false;
		}

		if(!isSorted) {
			System.out.println(-1);
		} else {
			System.out.println(steps);
			for(int i = 0; i < list.size(); i = i + 3) {
				System.out.println(list.get(i) + " " + list.get(i + 1) + " " + list.get(i + 2));
			}
		}

	}





	/*
	 * Helpers
	 */
	public static Map<Integer, List<Integer>> getCycles(int[] a) {
		Map<Integer, List<Integer>> cycles = new HashMap<Integer, List<Integer>>();
		Map<Integer, Integer> mapOfIntegersCoveredInCycles = new HashMap<Integer, Integer>();
		for(int i = 1;i < a.length; i++) {
			if(!mapOfIntegersCoveredInCycles.containsKey(i) && a[i] != i) {
				cycles.put(i, getCycle(a, i, mapOfIntegersCoveredInCycles));
			}
		}
		return cycles;
	}

	public static List<Integer> getCycle(int[] a, int index, Map<Integer, Integer> mapOfIntegersCoveredInCycles) {
		List<Integer> cycle = new ArrayList<Integer>();
		cycle.add(index);
		mapOfIntegersCoveredInCycles.put(index, index);
		int tempIndex = index;
		do {
			tempIndex = a[tempIndex];
			if(tempIndex != index) {
				mapOfIntegersCoveredInCycles.put(tempIndex, tempIndex);
				cycle.add(tempIndex);
			}
		} while(tempIndex != index);
		return cycle;
	}

	public static boolean checkForCount(int count) {
		boolean flag = true;
		if(count == 0) {
			System.out.println(0);
			flag = false;
		} 
		
		return flag;
	}

	public static void updateOutputList(int i, int j, int k, List<Integer> list) {
		list.add(i);
		list.add(j);
		list.add(k);
	} 

}
