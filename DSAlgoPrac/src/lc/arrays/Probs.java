package lc.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

class ElementIndexPair {
	int element;
	int index;
	public ElementIndexPair(int element, int index) {
		super();
		this.element = element;
		this.index = index;
	}
}


class Pair {
	int i;
	int j;
	public Pair(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}
}

class AbsDiffElementPair {
	int diff;
	int element;
	public AbsDiffElementPair(int diff, int element) {
		super();
		this.diff = diff;
		this.element = element;
	}
}

class AbsDiffElementPairCompare implements Comparator<AbsDiffElementPair> {

	@Override
	public int compare(AbsDiffElementPair o1, AbsDiffElementPair o2) {
		if(o1.diff > o2.diff) {
			return -1;
		} else if(o1.diff < o2.diff) {
			return 1;
		} else if(o1.element > o2.element) {
			return -1;
		} else if(o1.element < o2.element) {
			return 1;
		}
		return 0;
	}

}

class SortIntervals implements Comparator<int[]> {

	@Override
	public int compare(int[] o1, int[] o2) {
		return o1[0] - o2[0];
	}

}

public class Probs {

	public static int NORTH = 0;
	public static int SOUTH = 2;
	public static int WEST = 1;
	public static int EAST = 3;

	public static void main(String[] args) {
		int[] nums  = {1,2,2,2,5,0};
		System.out.println(new Probs().waysToSplit(nums));
	}

	public int waysToSplit(int[] nums) {
		int[] prefixSum = getPrefixSumArray(nums);
		int count = 0;

		int i = 0, j = 0, k = 0;

		for(i = 0; i < nums.length - 2; i++) {
			if(i >= j) {
				j++;
			}
			while(j < nums.length - 1 && 2 * getSum(prefixSum, 0, i) > getSum(prefixSum, 0, j)) {
				j++;
			}
			
			if(k < j) {
				k = j;
			}
			while(k < nums.length && 2 * getSum(prefixSum, 0, k) <= getSum(prefixSum, 0, nums.length - 1) + getSum(prefixSum, 0, i)) {
				k++;
			}
			count += k - j;
			if(count >= 1000000007) {
				count = count % 1000000007;
			}	
		}

		return count;
	}

	public static int getSum(int[] prefixSum, int i, int j) {
		int sum = 0;
		if(i == 0) {
			sum = prefixSum[j];
		} else {
			sum = prefixSum[j] - prefixSum[i - 1];
		}
		return sum;
	}

	public int[] getPrefixSumArray(int[] nums) {
		int[] prefixSum = new int[nums.length];
		prefixSum[0] = nums[0];

		for(int i = 1; i < nums.length; i++) {
			prefixSum[i] = prefixSum[i - 1] + nums[i];
		}

		return prefixSum;
	}

	public int maxScore(int[] nums, int k) {

		int[] left = new int[k];
		int[] right = new int[k];
		left[0] = nums[0];
		right[0] = nums[nums.length - 1];

		for(int i = 1;i < k; i++) {
			left[i] = left[i - 1] + nums[i];
			right[i] = right[i - 1] + nums[nums.length - i - 1];
		}

		int maxSum = 0;

		for(int i = 0;i <= k; i++) {
			int leftSum = (i == 0) ? 0 : left[i - 1]; 
			int rightSum = 0;
			if(i == 0) {
				rightSum = right[k - 1]; 
			} else if(i < k){
				rightSum = right[k - i - 1];
			}

			if(leftSum + rightSum > maxSum) {
				maxSum = leftSum + rightSum;
			}
		}

		return maxSum;
	}

	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		PriorityQueue<AbsDiffElementPair> pq = new PriorityQueue<AbsDiffElementPair>(new AbsDiffElementPairCompare());

		for(int i = 0;i < arr.length; i++) {
			int element = arr[i];
			int diff = Math.abs(element - x);

			if(pq.size() < k) {
				pq.add(new AbsDiffElementPair(diff, element));
			} else {
				if(pq.peek().diff > diff) {
					pq.poll();
					pq.add(new AbsDiffElementPair(diff, element));
				}
			}
		}

		List<Integer> ans = new ArrayList<Integer>();
		while(!pq.isEmpty()) {
			ans.add(pq.poll().element);
		}

		Collections.sort(ans);

		return ans;
	}

	public boolean carPooling(int[][] trips, int capacity) {
		boolean sol = false;
		return sol;
	}

	public boolean checkSubarraySum(int[] nums, int k) {
		int sum = 0;
		boolean exists = false;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);

		for(int i = 0;i < nums.length; i++) {
			sum += nums[i];
			int sumModK = sum % k;
			if(sumModK < 0) {
				sumModK += k;
			}
			if(map.containsKey(sumModK)) {
				int index = map.get(sumModK);
				if(index + 1 < i) {
					exists = true;
					break;
				}
			} else {
				map.put(sumModK, i);
			}
		}

		return exists;
	}
	public int subarraysDivByK(int[] nums, int k) {
		int count = 0, sum = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 1);

		for(int x : nums) {
			sum += x;
			int sumModK = sum % k;
			if(sumModK < 0) {
				sumModK += k;
			}
			if(map.containsKey(sumModK)) {
				count += map.get(sumModK);
			}
			map.put(sumModK, 1 + map.getOrDefault(sumModK, 0));
		}

		return count;
	}

	public boolean increasingTriplet(int[] nums) {
		boolean exists = false;
		int small = Integer.MAX_VALUE, large = Integer.MAX_VALUE;

		for(int x : nums) {
			if(x <= small) {
				small = x;
			} else if(x <= large) {
				large = x;
			} else {
				exists = true;
				break;
			}
		}

		return exists;
	}

	public static int[][] merge(int[][] intervals) {
		if(intervals.length == 1) {
			return intervals;
		}
		Arrays.parallelSort(intervals, new SortIntervals());
		List<Pair> mergedList = new LinkedList<Pair>();
		int count = 0;
		int parentIntervalI = intervals[0][0], parentIntervalJ = intervals[0][1];
		mergedList.add(new Pair(parentIntervalI, parentIntervalJ));

		for(int i = 1;i < intervals.length; i++) {
			if(areOverlapping(parentIntervalI, parentIntervalJ, intervals[i][0], intervals[i][1])) {
				if(parentIntervalJ < intervals[i][1]) {
					parentIntervalJ = intervals[i][1];	
					mergedList.get(count).j = parentIntervalJ;
				}
			} else {
				parentIntervalI = intervals[i][0];
				parentIntervalJ = intervals[i][1];
				mergedList.add(new Pair(parentIntervalI, parentIntervalJ));
				count++;
			}
		}

		int[][] mergedIntervals = new int[count + 1][2];
		for(int i = 0;i <= count; i++) {
			mergedIntervals[i][0] = mergedList.get(i).i;
			mergedIntervals[i][1] = mergedList.get(i).j;
		}

		return mergedIntervals;
	}

	public static boolean areOverlapping(int i1, int j1, int i2, int j2) {
		return j1 >= i2;
	}


	public static int stoneGameVII(int[] stones) {
		int alice = 0, bob = 0, sum = 0;
		int beginIndex = 0, endIndex = stones.length - 1;
		boolean aliceTurn = true;

		for(int x : stones) {
			sum += x;
		}

		while(beginIndex <= endIndex) {
			if(aliceTurn) {
				if(stones[beginIndex] < stones[endIndex]) {
					sum = sum - stones[beginIndex];
					alice += sum;
					beginIndex++;
				} else {
					sum = sum - stones[endIndex];
					alice += sum;
					endIndex--;
				}
			} else {
				if(stones[beginIndex] < stones[endIndex]) {
					sum = sum - stones[beginIndex];
					bob += sum;
					beginIndex++;
				} else {
					sum = sum - stones[endIndex];
					bob += sum;
					endIndex--;
				}
			}
			aliceTurn = !aliceTurn;
		}
		return alice - bob;
	}

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		double median = 0;
		int m = nums1.length, n = nums2.length;

		if(m > n) {
			int[] temp = nums2;
			nums2 = nums1;
			nums1 = temp;
			m = nums1.length; 
			n = nums2.length;
		}

		int beginIndex = 0, endIndex = m;

		while(beginIndex <= endIndex) {
			int i = getMid(beginIndex, endIndex), j = getSecondIndex(m, n, i);
			int maxA = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
			int maxB = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];

			int minA = (i == m) ? Integer.MAX_VALUE : nums1[i];
			int minB = (j == n) ? Integer.MAX_VALUE : nums2[j];

			if(maxA <= minB && maxB <= minA) {
				if((m + n) % 2 == 0) {
					median = ((double)Math.max(maxA, maxB) + ((double)Math.min(minA, minB)))/2;
					break;
				} else {
					median = (double)Math.max(maxA, maxB);
					break;
				}
			} else if(maxA > minB) {
				endIndex = i - 1;
			} else {
				beginIndex = i + 1;
			}
		}

		return median;
	}

	public static int getSecondIndex(int m, int n, int i) {
		return (m + n + 1)/2 - i;
	}

	public static int getMid(int beginIndex, int endIndex) {
		return (endIndex - beginIndex)/2 + beginIndex;
	}


	public int[] maxSlidingWindow(int[] nums, int k) {
		int n = nums.length, noOfWindows = n - k + 1, index = 0;
		int[] maxs = new int[noOfWindows];
		Deque<ElementIndexPair> dll = new LinkedList<ElementIndexPair>();

		for(int i = 0; i < n; i++) {
			cleanQueueFront(dll, nums, i, k);
			cleanQueueTail(dll, nums, i, k);
			dll.addLast(new ElementIndexPair(nums[i], i));
			if(i >= k - 1) {
				maxs[index++] = dll.peekFirst().element;	
			}
		}

		return maxs;
	}

	public void cleanQueueFront(Deque<ElementIndexPair> dll, int[] nums, int i, int k) {
		while(!dll.isEmpty() && dll.peekFirst().index <= (i - k)) {
			dll.pollFirst();
		}
	}

	public void cleanQueueTail(Deque<ElementIndexPair> dll, int[] nums, int i, int k) {
		while(!dll.isEmpty() && dll.peekLast().element < nums[i]) {
			dll.pollLast();
		}
	}


	public int firstMissingPositive(int[] nums) {
		int n = nums.length, missineNumber = n + 1;

		for(int i = 0;i < n; i++) {
			while(isInterested(nums, i)) {
				swap(nums, i, nums[i] - 1);				
			}
		}

		for(int i = 0;i < n; i++) {
			if((nums[i] - 1) != i) {
				missineNumber = i + 1;
				break;
			} 
		}

		return missineNumber;
	}

	public boolean isInterested(int[] nums, int i) {
		return nums[i] >= 1 && nums[i] < (nums.length) && (nums[i] - 1) != i && nums[nums[i] - 1] != nums[i];
	}

	public void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public void rotate(int[][] matrix) {
		int oldElement = 0, newElement = 0, rowLast = matrix.length - 1, colLast = matrix[0].length - 1;
		int rowOffset = 0, colOffset = 0;
		for(int row = 0;row < matrix.length; row++) {
			for(int col = row;col < matrix[row].length - row - 1; col++) {

				oldElement = matrix[row][col];
				newElement = matrix[row + rowOffset][colLast];
				matrix[row + rowOffset][colLast] = oldElement;

				oldElement = newElement;
				newElement = matrix[rowLast][colLast - colOffset];
				matrix[rowLast][colLast - colOffset] = oldElement;

				oldElement = newElement;
				newElement = matrix[rowLast - rowOffset][col - colOffset];
				matrix[rowLast - rowOffset][col - colOffset] = oldElement;

				oldElement = newElement;
				matrix[row][col] = oldElement;

				rowOffset++;
				colOffset++;
			}
			rowOffset = 0;
			colOffset = 0;
			rowLast --;
			colLast--;
		}
	}

	public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
		long maxArea = 1;
		Arrays.sort(horizontalCuts);
		Arrays.sort(verticalCuts);

		long maxHeight = horizontalCuts[0];
		for(int i = 1; i < horizontalCuts.length; i++) {
			if((horizontalCuts[i] - horizontalCuts[i - 1]) > maxHeight) {
				maxHeight = horizontalCuts[i] - horizontalCuts[i - 1];
			}
		}

		maxHeight = Math.max(maxHeight, h - horizontalCuts[horizontalCuts.length - 1]);

		long maxWidth = verticalCuts[0];
		for(int i = 1; i < verticalCuts.length; i++) {
			if((verticalCuts[i] - verticalCuts[i - 1]) > maxWidth) {
				maxWidth = verticalCuts[i] - verticalCuts[i - 1];
			}
		}

		maxWidth = Math.max(maxWidth, w - verticalCuts[verticalCuts.length - 1]);
		maxArea = maxHeight * maxWidth;
		if(maxArea >= 1000000007) {
			maxArea = maxArea % 1000000007;
		}
		//        System.out.println(maxArea);
		return (int)maxArea;
	}

	public boolean isRobotBounded(String instructions) {
		int x = 0, y = 0;
		int CD = NORTH;
		for(int j = 0;j <= 3; j++) {
			for(int i = 0;i < instructions.length(); i++) {
				char I = instructions.charAt(i);
				if(I == 'G') {
					if(CD == NORTH) {
						y++;
					} else if(CD == SOUTH) {
						y--;
					} else if(CD == EAST) {
						x++;
					} else {
						x--;
					}
				} else {
					if(I == 'L') {
						CD = (CD + 1) % 4;
					} else {
						CD = (CD == 0) ? EAST : (CD - 1);
					}
				}
			}	
		}
		return x == 0 && y == 0;
	}

	public int maxSubArray(int[] nums) {
		int maxSum = nums[0], currSum = nums[0];
		for(int i = 1; i < nums.length; i++) {
			if((currSum + nums[i]) > nums[i]) {
				currSum = currSum + nums[i];
			} else {
				currSum = nums[i];
			}
			if(maxSum < currSum) {
				maxSum = currSum;
			}
		}
		return maxSum;
	}

	public int subarraySum(int[] nums, int k) {
		int count = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int sum = 0;

		for(int i = 0;i < nums.length; i++) {
			sum = sum + nums[i];
			if(sum == k) {
				count++;
			}
			if(map.containsKey(sum - k)) {
				count = count + map.get(sum - k);
			}

			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;
	}

	public int minSubArray(int[] nums) {
		int minSum = nums[0], currSum = nums[0];
		for(int i = 1; i < nums.length; i++) {
			if((currSum + nums[i]) < nums[i]) {
				currSum = currSum + nums[i];
			} else {
				currSum = nums[i];
			}
			if(minSum > currSum) {
				minSum = currSum;
			}
		}
		return minSum;
	}

	public int maxSubarraySumCircular(int[] nums) {
		int total = 0;
		for(int x : nums) {
			total += x;
		}
		int maxNormal = maxSubArray(nums);
		if(maxNormal < 0) {
			return maxNormal;
		}
		return Math.max(maxNormal, total - minSubArray(nums));
	}

	public int maxArea(int[] height) {
		int beginIndex = 0, endIndex = height.length - 1;
		int maxArea = 0;
		while(beginIndex < endIndex) {
			int minHeightIndex = -1;
			int diff = endIndex - beginIndex;
			if(height[endIndex] < height[beginIndex]) {
				minHeightIndex = endIndex;
				endIndex--;
			} else {
				minHeightIndex = beginIndex;
				beginIndex++;
			}
			int currArea = diff * height[minHeightIndex];
			if(currArea > maxArea) {
				maxArea = currArea;
			}
		}
		return maxArea;
	}
}
