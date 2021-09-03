package lc.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Problems2 {
	static int k = 0;
	Boolean[][] dpArray;

	int[][] dp1;

	public static void main(String[] args) {
		int[] arr = {3, 4, 5};
		System.out.println(new Problems2().longestPalindromeSubseqBottomUp("abkccbc"));
	}
	
	public int longestPalindromeSubseq(String s) {
		dp1 = new int[s.length()][s.length()];
		for(int i = 0;i < s.length(); i++) {
			for(int j = 0;j < s.length(); j++) {
				dp1[i][j] = -1;
			}
		}
		return longestPalindromeSubseqSolve(s, 0, s.length() - 1);
    }
	
	public int longestPalindromeSubseqBottomUp(String s) {
		dp1 = new int[s.length()][s.length()];
		
		for(int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			for(int j = i; j < s.length(); j++) {
				if(i == j) {
					dp1[i][j] = 1;
				} else {
					if(c == s.charAt(j)) {
						dp1[i][j] = 2 + dp1[i + 1][j - 1];
					} else {
						dp1[i][j] = Math.max(dp1[i + 1][j], dp1[i][j - 1]);
					}	
				}
			}
		}
		
		return dp1[0][s.length() - 1];
	}
	
	public int longestPalindromeSubseqSolve(String s, int beginIndex, int endIndex) {
		if(beginIndex > endIndex) {
			return 0;
		}
		
		if(beginIndex == endIndex) {
			return 1;
		}
		
		if(dp1[beginIndex][endIndex] > -1) {
			return dp1[beginIndex][endIndex];
		}
		
		if(s.charAt(beginIndex) == s.charAt(endIndex)) {
			dp1[beginIndex][endIndex] =  2 + longestPalindromeSubseqSolve(s, beginIndex + 1, endIndex - 1);
		} else {
			dp1[beginIndex][endIndex] =  Math.max(
					longestPalindromeSubseqSolve(s, beginIndex + 1, endIndex), 
					longestPalindromeSubseqSolve(s, beginIndex, endIndex - 1)
					);	
		}
		return dp1[beginIndex][endIndex]; 
	}

	public String shortestCommonSupersequence(String str1, String str2) {
		setLCSDPTable(str1, str2);
		StringBuilder stringBuilder = new StringBuilder();
		int i = str1.length(), j = str2.length();
		
		while(i > 0 && j > 0) {
			if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
				stringBuilder.append(str1.charAt(i - 1));
				i--;
				j--;
			} else if(dp1[i][j - 1] > dp1[i - 1][j]) {
				stringBuilder.append(str2.charAt(j - 1));
				j--;
			} else {
				stringBuilder.append(str1.charAt(i - 1));
				i--;
			}
		}
		
		while(i > 0) {
			stringBuilder.append(str1.charAt(i - 1));
			i--;
		}
		
		while(j > 0) {
			stringBuilder.append(str2.charAt(j - 1));
			j--;
		}
		
		return stringBuilder.reverse().toString();
	}

	public void setLCSDPTable(String str1, String str2) {
		dp1 = new int[str1.length() + 1][str2.length() + 1]; 
		for(int i = 1;i <= str1.length(); i++) {
			for(int j = 1;j <= str2.length(); j++) {
				if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dp1[i][j] = 1 + dp1[i - 1][j - 1];
				} else {
					dp1[i][j] = Math.max(dp1[i - 1][j], dp1[i][j - 1]);
				}
			}
		}
	}

	int longestCommonSubstr(String S1, String S2, int n, int m){
		return longestCommonSubstrSolve(S1, S2, n, m, 0);
	}


	int longestCommonSubstrSolve(String S1, String S2, int n, int m, int count){

		if(n == 0 || m == 0) {
			return count;
		}

		if(S1.charAt(n - 1) == S2.charAt(m - 1)) {
			return longestCommonSubstrSolve(S1, S2, n - 1, m - 1, 1 + count);
		} else {
			return Math.max(count, Math.max(
					longestCommonSubstrSolve(S1, S2, n - 1, m, 0),
					longestCommonSubstrSolve(S1, S2, n, m - 1, 0)
					));
		}
	}

	public int longestCommonSubsequence(String text1, String text2) {
		dp1 = new int[text1.length() + 1][text2.length() + 1];
		//		for(int i = 0;i <= text1.length(); i++) {
		//			for(int j = 0;j <= text2.length(); j++) {
		//				dp1[i][j] = -1;
		//			}
		//		}
		//        return longestCommonSubsequenceRec(text1, text2, text1.length(), text2.length());
		return longestCommonSubsequenceDP(text1, text2, text1.length(), text2.length());
	}

	public int longestCommonSubsequenceDP(String text1, String text2, int n1, int n2) {
		for(int i = 1;i <= n1; i++) {
			for(int j = 1;j <= n2; j++) {
				if(text1.charAt(i - 1) == text2.charAt(j - 1)) {
					dp1[i][j] = 1 + dp1[i - 1][j - 1];
				} else {
					dp1[i][j] = Math.max(dp1[i][j - 1], dp1[i - 1][j]);
				}
			}
		}
		return dp1[n1][n2];
	}

	public int longestCommonSubsequenceRec(String text1, String text2, int n1, int n2) {
		if(n1 == 0 || n2 == 0) {
			dp1[n1][n2] = 0;
			return 0;
		}
		if(dp1[n1][n2] > -1) {
			return dp1[n1][n2];
		} 
		if(text1.charAt(n1 - 1) == text2.charAt(n2 - 1)) {
			dp1[n1][n2] = 1 + longestCommonSubsequenceRec(text1, text2, n1 - 1, n2 - 1);
		} else {
			dp1[n1][n2] = Math.max(longestCommonSubsequenceRec(text1, text2, n1 - 1, n2), 
					longestCommonSubsequenceRec(text1, text2, n1, n2 - 1));
		}
		return dp1[n1][n2];
	}

	public int coinChange(int[] coins, int amount) {
		dp1 = new int[coins.length + 1][amount + 1];

		for(int i = 0;i <= amount; i++) {
			dp1[0][i] = Integer.MAX_VALUE - 1;
		}

		for(int i = 1; i <= coins.length; i++) {
			for(int j = 1; j <= amount; j++) {
				if(coins[i - 1] > j) {
					dp1[i][j] = dp1[i - 1][j];
				} else {
					dp1[i][j] = Math.min(dp1[i - 1][j], 1 + dp1[i][j - coins[i - 1]]);
				}
			}
		}

		return (dp1[coins.length][amount] == Integer.MAX_VALUE - 1) ? -1 : dp1[coins.length][amount];
	}

	public int coinChangeSolve(int[] coins, int amount, int n) {
		if(n == 0) {
			return Integer.MAX_VALUE - 1;
		}

		if(amount == 0) {
			return 0;
		}

		if(dp1[n][amount] > 0) {
			return dp1[n][amount];
		}

		int sol = 0;

		if(coins[n - 1] > amount) {
			sol = coinChangeSolve(coins, amount, n - 1);
		} else {
			sol = Math.min(coinChangeSolve(coins, amount, n - 1), 1 + coinChangeSolve(coins, amount - coins[n - 1], n));	
		}
		dp1[n][amount] = sol;
		return sol;
	}

	public int change(int amount, int[] coins) {
		dp1 = new int[coins.length + 1][amount + 1];

		for(int i = 0;i < dp1.length; i++) {
			for(int j = 0;j < dp1[0].length; j++) {
				if(j == 0) {
					dp1[i][j] = 1;
				} else if(i == 0) {
					dp1[i][j] = 0;
				} else if(j < coins[i - 1]) {
					dp1[i][j] = dp1[i - 1][j];
				} else {
					dp1[i][j] = dp1[i - 1][j] + dp1[i][j - coins[i - 1]];
				}
			}
		}

		return dp1[coins.length][amount];
	}

	public int changeSolve(int amount, int[] coins, int n) {
		//		if(dp1[amount][n] > -1) {
		//			return dp1[amount][n];
		//		}



		if(amount == 0) {
			dp1[amount][n] = 1;
			return 1;
		}

		if(n == 0) {
			dp1[amount][n] = 0;
			return 0;
		}

		int ways = 0;

		if(coins[n - 1] > amount) {
			ways = changeSolve(amount, coins, n - 1);
		} else {
			ways = changeSolve(amount, coins, n - 1) + changeSolve(amount - coins[n - 1], coins, n);
		}
		dp1[amount][n] = ways;
		return ways;
	}

	public int findTargetSumWays(int[] nums, int target) {
		int tot = 0;
		int c = 0;
		for(int x : nums) {
			tot += x;
			if(x == 0) 
				c++;
		}
		int sum = (target + tot)/2;
		if(tot < target || (tot + target) % 2 > 0) {
			return 0;
		}

		int[][] dpArray = new int[nums.length + 1][sum + 1];

		for(int i = 0;i <= nums.length; i++) {
			for(int j = 0;j <= sum; j++) {
				if(i > 0 && nums[i - 1] == 0) {
					dpArray[i][j] = dpArray[i - 1][j];
				} else if(j == 0) {
					dpArray[i][j] = 1;
				} else if(j > 0 && i == 0) {
					dpArray[i][j] = 0;
				} else if(nums[i - 1] > j) {
					dpArray[i][j] = dpArray[i - 1][j];
				} else {
					dpArray[i][j] = dpArray[i - 1][j] 
							+ dpArray[i - 1][j - nums[i - 1]];
				}
			}    
		}
		return (int)Math.pow(2, c) * dpArray[nums.length][sum];
	}

	public int subsetSumCount(int[] nums, int sum, int n) {
		if(sum == 0) {
			return 1;
		}
		if(n == 0) {
			return 0;
		}

		if(dp1[n][sum] >= 0) {
			return dp1[n][sum];
		}

		if(sum < nums[n - 1]) {
			dp1[n][sum] = subsetSumCount(nums, sum, n - 1);
		} else {
			dp1[n][sum] = subsetSumCount(nums, sum, n - 1) + subsetSumCount(nums, sum - nums[n - 1], n - 1);	
		}
		return dp1[n][sum];
	}


	public int getSum(int[] nums) {
		int sum = 0;
		for(int x : nums) {
			sum += x;
		}
		return sum;
	}


	public int lastStoneWeightII(int[] stones) {
		int sum = getSum(stones);
		boolean[][] dpArray = getDpArray(stones, sum);
		int i = sum / 2;

		while(i >= 0 && !dpArray[stones.length][i]) {
			i--;
		}
		return sum - 2 * i;

	}

	public boolean[][] getDpArray(int[] stones, int sum) {
		boolean[][] dpArray = new boolean[stones.length + 1][sum + 1];

		for(int i = 0;i <= stones.length; i++) {
			for(int j = 0;j <= sum; j++) {
				if(j == 0) {
					dpArray[i][j] = true;
				} else if(i == 0) {
					dpArray[i][j] = false;
				} else if(stones[i - 1] > j) {
					dpArray[i][j] = dpArray[i - 1][j];
				} else {
					dpArray[i][j] = dpArray[i - 1][j] 
							|| dpArray[i - 1][j - stones[i - 1]];
				}
			}    
		}

		return dpArray;
	}

	public boolean canPartition(int[] nums) {
		int sum = getSum(nums);
		return sum % 2 == 0 && subsetSum1(nums, sum / 2);
	}

	public boolean subsetSum1(int[] nums, int sum) {
		boolean[] dpArray = new boolean[sum + 1];

		dpArray[0] = true;

		for(int i = 0;i < nums.length; i++) {
			for(int j = sum;j >= nums[i]; j--) {
				dpArray[j] = dpArray[j] || dpArray[j - nums[i]];
			}
		}

		return dpArray[sum];
	}

	public boolean subsetSum(int[] nums, int sum, int n) {
		if(sum == 0) {
			return true;
		}

		if(n == 0) {
			return false;
		}

		if(dpArray[n][sum] != null) {
			return dpArray[n][sum];
		}

		boolean isExists = false;

		if(sum < nums[n - 1]) {
			isExists =  subsetSum(nums, sum, n - 1);
		} else {
			isExists = subsetSum(nums, sum - nums[n - 1], n - 1)
					|| subsetSum(nums, sum, n - 1);	
		}

		dpArray[n][sum] = isExists;

		return isExists; 
	}

	static int knapSack(int W, int wt[], int val[], int n) { 
		if(n == 0 || W == 0) {
			return 0;
		}

		if(wt[n - 1] <= W) {
			return Math.max(val[n - 1] + knapSack(W - wt[n - 1], wt, val, n - 1), knapSack(W, wt, val, n - 1));
		}

		return knapSack(W, wt, val, n - 1);
	}

	public boolean canJump(int[] nums) {
		boolean[] dp = new boolean[nums.length];
		dp[nums.length - 1] = true;

		for(int index = nums.length - 2; index >= 0; index--) {
			int maxJumpLength = Math.min(nums[index], nums.length - index - 1);
			for(int i = 1;i <= maxJumpLength; i++) {
				if(dp[index + i]) {
					dp[index] = true;
					break;
				}
			}
		}

		return dp[0];
	}

	public boolean canJumpSolve(int[] dp, int[] nums, int index) {
		if(index == nums.length - 1) {
			return true;
		}
		if(nums[index] == 0) {
			return false;
		}

		if(dp[index] == 1) {
			return true;
		}

		if(dp[index] == 2) {
			return false;
		}

		int maxJumpLength = Math.min(nums[index], nums.length - index - 1);

		for(int i = 1;i <= maxJumpLength; i++) {
			if(canJumpSolve(dp, nums, index + i)) {
				dp[index] = 1;
				return true;
			}
		}
		dp[index] = 2;
		return false;
	}

	public int oddEvenJumps(int[] arr) {
		int count = 0;
		for(int i = 0;i < arr.length; i++) {
			if(oddEvenJumpsSolve(arr, i, true)) {
				count++;
			}
		}
		return count;
	}

	public boolean oddEvenJumpsSolve(int[] arr, int index, boolean parity) {
		if(index == arr.length - 1) {
			return true;
		}

		if(index < 0) {
			return false;
		}

		int nextIndex = (parity) 
				? getNextHigherIndex(index, arr, arr.length) 
						: getNextLowerIndex(index, arr, arr.length);
				return oddEvenJumpsSolve(arr, nextIndex, !parity);

	}

	public int oddEvenJumps2(int[] arr) {
		int countOfGoodIndices = 1, n = arr.length;
		boolean[] higher = new boolean[n];
		boolean[] lower = new boolean[n];
		higher[n - 1] = true;
		lower[n - 1] = true;

		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		map.put(arr[n - 1], n - 1);

		for(int i = n - 2; i >= 0; i--) {
			Map.Entry hp = map.ceilingEntry(arr[i]);
			Map.Entry lp = map.floorEntry(arr[i]);

			int nextHigherIndex = (hp == null) ? -1 : (int)hp.getValue();
			int nextLowerIndex = (lp == null) ? -1 : (int)lp.getValue();

			if(nextHigherIndex != -1 && lower[nextHigherIndex]) {
				higher[i] = true;
			}

			if(nextLowerIndex != -1 && higher[nextLowerIndex]) {
				lower[i] = true;
			}

			if(higher[i]) {
				countOfGoodIndices++;
			} 

			map.put(arr[i], i);
		}

		return countOfGoodIndices;
	}


	public int getNextHigherIndex(int i, int[] arr, int n) {
		int element = arr[i++];
		int max = Integer.MAX_VALUE;
		int index = i;
		boolean flag = false;

		while(i < n) {
			if(arr[i] >= element && arr[i] < max) {
				max = arr[i];
				index = i;
				flag = true;
			}
			i++;
		}
		return (flag) ? index : -1; 
	}

	public int getNextLowerIndex(int i, int[] arr, int n) {
		int element = arr[i++];
		int min = Integer.MIN_VALUE;
		int index = i;
		boolean flag = false;

		while(i < n) {
			if(arr[i] <= element && arr[i] > min) {
				min = arr[i];
				index = i;
				flag = true;
			}
			i++;
		}
		return (flag) ? index : -1; 
	}

	public int countSubstrings(String s) {
		boolean[][] dpArray = new boolean[s.length()][s.length()];
		int count = 0;

		for(int i = 0;i < s.length(); i++) {
			int rowIndex = 0;
			int colIndex = i;
			while(rowIndex < s.length() - i) {
				if(i == 0) {
					dpArray[rowIndex][colIndex] = true;
					count++;
				} else if(i == 1 && s.charAt(rowIndex) == s.charAt(colIndex))  {
					dpArray[rowIndex][colIndex] = true;
					count++;
				} else if(s.charAt(rowIndex) == s.charAt(colIndex) 
						&& dpArray[rowIndex + 1][colIndex - 1]) {
					dpArray[rowIndex][colIndex] = true;
					count++;
				}
				rowIndex++;
				colIndex++;
			}
		}

		return count;
	}

	public int countSubstrings(String s, String t) {
		int count = 0;
		for(int i = 0;i < s.length(); i++) {
			for(int j = 0;j < t.length(); j++) {
				int mismatches = 0;
				for(int pos = 0; i + pos < s.length() && j + pos < t.length(); pos++) {
					if(s.charAt(i + pos) != t.charAt(j + pos) && ++mismatches > 1) {
						break;
					}
					count += mismatches;
				}
			}
		}
		return count;
	}

	public int twoEggDrop(int n) {
		dpArray = new int[n + 1][3];
		return twoEggDropSolve(n, 2);
	}

	public int twoEggDropSolve(int floors, int eggs) {
		if(eggs == 1) {
			return floors;
		}
		if(floors <= 1) {
			return floors;
		}

		if(dpArray[floors][eggs] != 0) {
			return dpArray[floors][eggs];
		}

		int min = Integer.MAX_VALUE;

		int begin = 1, end = floors;

		while(begin <= end) {
			int mid = getMid(begin, end);
			int brokenEggSteps = twoEggDropSolve(mid - 1, eggs - 1);
			int unbrokenEggSteps = twoEggDropSolve(floors - mid, eggs);
			int total = 1 + Math.max(brokenEggSteps, unbrokenEggSteps);
			min = Math.min(min, total);

			if(brokenEggSteps < unbrokenEggSteps) {
				begin = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		dpArray[floors][eggs] = min;
		return min;	
	}

	public int getMid(int a, int b) {
		return ((b - a) >> 1) + a;
	}

	public int numTeams(int[] rating) {
		int teamsCount = 0;

		for(int i = 0; i < rating.length; i++) {
			int leftGreaterCount = 0, leftSmallerCount = 0;
			int element = rating[i];

			for(int j = i - 1;j >= 0; j--) {
				if(element < rating[j]) {
					leftGreaterCount++;
				}
			}

			if(i > 0) {
				leftSmallerCount = i - leftGreaterCount;
				if(leftSmallerCount < 0) {
					leftSmallerCount = 0;
				}
			}

			int rightGreaterCount = 0, rightSmallerCount = 0;
			element = rating[i];

			for(int j = i + 1;j < rating.length; j++) {
				if(element < rating[j]) {
					rightGreaterCount++;
				}
			}

			if(i < rating.length - 1) {
				rightSmallerCount = rating.length - i - rightGreaterCount - 1;
				if(rightSmallerCount < 0) {
					rightSmallerCount = 0;
				}
			}

			teamsCount += leftGreaterCount * rightSmallerCount + leftSmallerCount * rightGreaterCount;
		}

		return teamsCount;
	}

	public int countSquares(int[][] matrix) {
		int count = 0;
		int rowsCount = matrix.length, colsCount = matrix[0].length;
		int[][] sumMatrix = new int[rowsCount][colsCount];

		for(int i = 0;i < rowsCount; i++) {
			for(int j = 0;j < colsCount; j++) {
				if(matrix[i][j] != 0) {
					if(i == 0 || j == 0) {
						sumMatrix[i][j] = 1;
					} else {
						sumMatrix[i][j] = 1 + Math.min(sumMatrix[i - 1][j], Math.min(sumMatrix[i][j - 1], sumMatrix[i - 1][j - 1]));
					}
					count += sumMatrix[i][j];
				}
			}
		}

		return count;
	}



	public int minDistance(String word1, String word2) {
		int[][] memo = new int[word1.length()][word2.length()];

		return minDistanceSolve(memo, word1, word2, 0, 0);
	}

	public int countVowelStrings(int n) {
		int[][] memo = new int[n + 1][6];
		for(int i = 0; i < memo.length; i++) {
			Arrays.fill(memo[i], -1); 
		}
		return countVowelStringsSolve(memo, n, 1);
	}

	public int countVowelStringsSolve(int[][] memo, int n, int pos) {
		if(n == 0) {
			k++;
			return 1;	
		}
		//		if(memo[n][pos] > -1) {
		//			return memo[n][pos];
		//		}
		int sum = 0;
		for(int i = pos; i <= 5; i++) {
			sum += countVowelStringsSolve(memo, n - 1, i);
		}
		memo[n][pos] = sum;
		return sum;
	}

	public int minDistanceSolve(int[][] memo, String word1, String word2, int beginIndex1, int beginIndex2) {
		if(beginIndex1 >= word1.length() && beginIndex2 >= word2.length()) {
			return 0;
		}

		if(beginIndex1 >= word1.length()) {
			return word2.length() - beginIndex2;
		}

		if(beginIndex2 >= word2.length()) {
			return word1.length() - beginIndex1;
		}

		if(memo[beginIndex1][beginIndex2] > 0) {
			return memo[beginIndex1][beginIndex2];
		}

		int minDis = 0;

		if(word1.charAt(beginIndex1) == word2.charAt(beginIndex2)) {
			minDis = minDistanceSolve(memo, word1, word2, beginIndex1 + 1, beginIndex2 + 1);
		} else {
			minDis = 1 + Math.min(
					minDistanceSolve(memo, word1, word2, beginIndex1 + 1, beginIndex2),
					minDistanceSolve(memo, word1, word2, beginIndex1, beginIndex2 + 1)
					);
		}

		memo[beginIndex1][beginIndex2] = minDis;

		return minDis;
	}

	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> lists = new LinkedList<List<Integer>>();
		solve(n, k, 0, lists, new LinkedList<Integer>());
		return lists;
	}

	public void solve(int n, int k, int currentPosition, List<List<Integer>> lists, List<Integer> list) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.get
		if(list.size() == k) {
			lists.add(new LinkedList<Integer>(list));
			return;
		}
		if(currentPosition > n) {
			return;
		}
		for(int i = currentPosition + 1; i <= n; i++) {
			list.add(i);
			solve(n, k, i, lists, list);
			list.remove(list.size() - 1);
		}
	}

	public int getMaximumGenerated(int n) {
		int max = 0;
		if(n == 0) {
			return max;
		} else if(n == 1) {
			return 1 + max;
		} else {
			max = 1;
			int[] array = new int[n + 1];
			array[1] = 1;

			for(int i = 2; i <= n; i++) {
				int base = i >> 1;
			if(i % 2 == 0) {
				array[i] = array[base];
			} else {
				array[i] = array[base] + array[i - base];
			}
			max = Math.max(max, array[i]);
			}

		}
		return max;
	}

	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> pascal = new ArrayList<List<Integer>>();
		List<Integer> row = new ArrayList<Integer>();
		row.add(1);
		pascal.add(row);
		int rowIndex = 1;

		while(rowIndex < numRows) {
			List<Integer> newRow = new ArrayList<Integer>();
			newRow.add(1);
			for(int i = 0;i < pascal.get(rowIndex- 1).size() - 1; i++) {
				newRow.add(pascal.get(rowIndex- 1).get(i) + pascal.get(rowIndex- 1).get(i + 1));
			}
			newRow.add(1);
			pascal.add(newRow);
			rowIndex++;
		}

		return pascal;
	}

	public int rotatedDigits(int n) {
		int count = 0;
		int[] dpArray = new int[n + 1];

		for(int i = 0; i <= n; i++) {
			if(i < 10) {
				if(i == 0 || i == 1  || i == 8) {
					dpArray[i] = 1;
				} else if(i == 5 || i == 2 || i == 6 || i == 9) {
					count++;
					dpArray[i] = 2;
				}
			} else {
				int mod = i % 10, div = i / 10;
				if(dpArray[mod] == 1 && dpArray[div] == 1) {
					dpArray[i] = 1;
				} else if(dpArray[mod] >= 1 && dpArray[div] >= 1) {
					dpArray[i] = 2;
					count++;
				}
			}
		}

		return count;
	}

	public boolean valid(int n) {
		boolean isValid = false;

		while(n > 0) {
			int mod = n  % 10;
			if(mod == 3 || mod == 4 || mod == 7) {
				isValid = false;
				break;
			} else if(mod == 2 || mod == 5 || mod == 6 || mod == 9) {
				isValid = true;
			}
			n = n / 10;
		}

		return isValid;
	}

	public int climbStairs(int[] memo, int n) {
		if(n < 0) {
			return 0;
		}

		if(n <= 1) {
			return 1;
		}

		if(memo[n] >= 0) {
			return memo[n];
		}

		int solve = climbStairs(memo, n - 1) + climbStairs(memo, n - 2);
		memo[n] = solve;
		return solve;
	}

}
