package lc.google;


class MaxLeafMinSumPair {
	int maxLeaf;
	int minSum;
	
	public MaxLeafMinSumPair(int maxLeaf, int minSum) {
		super();
		this.maxLeaf = maxLeaf;
		this.minSum = minSum;
	}
}

public class MinCostTreeFromLeafValues {

	MaxLeafMinSumPair[][] dp;
	
	public static void main(String[] args) {
		int[] arr = {6, 2, 4};
		System.out.println((new MinCostTreeFromLeafValues().mctFromLeafValues(arr)));
	}
	
	public int mctFromLeafValues(int[] arr) {
		dp = new MaxLeafMinSumPair[arr.length][arr.length];
		return mctFromLeafValues(arr, 0, arr.length - 1).minSum;
    }
	
	
	public MaxLeafMinSumPair mctFromLeafValues(int[] arr, int startIndex, int endIndex) {
		if(startIndex == endIndex) {
			return new MaxLeafMinSumPair(arr[startIndex], 0);
		}
		
		if(dp[startIndex][endIndex] != null) {
			return dp[startIndex][endIndex];
		}
		
		int maxLeaf = Integer.MIN_VALUE;
		int minSum = Integer.MAX_VALUE;
		
		for(int i = startIndex; i < endIndex; i++) {
			MaxLeafMinSumPair leftPair = mctFromLeafValues(arr, startIndex, i);
			MaxLeafMinSumPair rightPair = mctFromLeafValues(arr, i + 1, endIndex);
			
			maxLeaf = Math.max(leftPair.maxLeaf, maxLeaf);
			maxLeaf = Math.max(rightPair.maxLeaf, maxLeaf);
		
			int prod = leftPair.maxLeaf * rightPair.maxLeaf;
			minSum = Math.min(minSum, prod + leftPair.minSum + rightPair.minSum);
		}
		
		dp[startIndex][endIndex] = new MaxLeafMinSumPair(maxLeaf, minSum);
		
		return dp[startIndex][endIndex];
    }

}
