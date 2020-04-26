package greedy.algorithms;

public class JobSequencingProblem {

	static class JSPair {
		int deadline;
		int profit;
		
		public JSPair(int deadline, int profit) {
			this.deadline = deadline;
			this.profit = profit;
		}
	}
	
	public static void main(String[] args) {
		JSPair[] jsPairs = {
				new JSPair(5, 80),
				new JSPair(4, 50),
				new JSPair(1, 20),
				new JSPair(5, 10),
				new JSPair(1, 5)
		};
		maxProfitPossible(jsPairs);
	}
	
	/*
	 * Assumes JSPAirs are sorted in decreasing order of profit
	 */
	public static void maxProfitPossible(JSPair[] jsPairs) {
		int maxProfit = jsPairs[0].profit;
		int[] slots = new int[getMaxDeadline(jsPairs)];
		slots[slots.length - 1] = maxProfit;
		
		for(int i = 1; i < jsPairs.length; i++) {
			JSPair currentPair = jsPairs[i];
			int currentDeadlineIndex = currentPair.deadline - 1;
			while(currentDeadlineIndex >= 0 && slots[currentDeadlineIndex] != 0) {
				currentDeadlineIndex--;
			}
			if(currentDeadlineIndex >= 0) {
				slots[currentDeadlineIndex] = currentPair.profit;
			}
		}
	}
	
	public static int getMaxDeadline(JSPair[] jsPairs) {
		int ans = -1;
		for(JSPair jsPair : jsPairs) {
			ans = Math.max(ans, jsPair.deadline);
		}
		return ans;
	}

}
