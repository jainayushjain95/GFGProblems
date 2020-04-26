package greedy.algorithms;

public class FractionalKnapsackProblem {

	static class KnapsackPair {
		int value;
		int weight;
		
		public KnapsackPair(int weight, int value) {
			this.value = value;
			this.weight = weight;
		}
		
	}
	
	public static void main(String[] args) {
		KnapsackPair[] knapsackPairs = {
				new KnapsackPair(20, 500),
				new KnapsackPair(30, 400),
				new KnapsackPair(50, 600)
		};
		maxValuePossible(knapsackPairs, 70);
	}
	
	/*
	 * Assume it is sorted in decreasing order of value/weight
	 */
	public static void maxValuePossible(KnapsackPair[] knapsackPairs, int maxBagCapacity) {
		int currentCapacity = maxBagCapacity, result = 0;
		
		for(int i = 0;i < knapsackPairs.length; i++) {
			KnapsackPair currKnapsackPair = knapsackPairs[i];
			if(currKnapsackPair.weight <= currentCapacity) {
				currentCapacity -= currKnapsackPair.weight;
				result += currKnapsackPair.value;
			} else {
				result += currentCapacity * (currKnapsackPair.value / currKnapsackPair.weight);
				break;
			}
		}
		System.out.println(result);
	}
	
}
