package lc.arrays;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class ElementIndexPair {
	int element;
	int index;
	public ElementIndexPair(int element, int index) {
		super();
		this.element = element;
		this.index = index;
	}
}

public class Probs {

	public static int NORTH = 0;
	public static int SOUTH = 2;
	public static int WEST = 1;
	public static int EAST = 3;

	public static void main(String[] args) {
		int[] nums = {1,3,-1,-3,5,3,6,7};
		System.out.println((new Probs()).maxSlidingWindow(nums, 3));
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
