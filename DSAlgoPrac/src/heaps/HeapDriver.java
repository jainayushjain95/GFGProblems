package heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) { this.val = val; }
	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Pair {
	int matrixIndex;
	int elementIndex;

	public Pair(int matrixIndex, int elementIndex) {
		super();
		this.matrixIndex = matrixIndex;
		this.elementIndex = elementIndex;
	}
}

class MaxHeap {
	int[] data;
	int size;
	int capacity;

	public MaxHeap(int capacity) {
		data = new int[capacity];
		size = 0;
		this.capacity = capacity;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getLeftIndex(int currentIndex) {
		return 2*currentIndex + 1;
	}

	public int getRightIndex(int currentIndex) {
		return 2*currentIndex + 2;
	}

	public int getParentIndex(int currentIndex) {
		return (currentIndex - 1)/2;
	}

	public void insert(int element) {
		data[size] = element;
		fixHeapAfterInsertion(size++);
	}

	public void swap(int beginIndex, int endIndex) {
		int temp = data[beginIndex];
		data[beginIndex] = data[endIndex];
		data[endIndex] = temp;
	}

	public void fixHeapAfterInsertion(int currentIndex) {
		if(currentIndex <= 0) {
			return;
		}
		int parentIndex = getParentIndex(currentIndex);
		while(currentIndex > 0 && data[parentIndex] < data[currentIndex]) {
			swap(currentIndex, parentIndex);
			currentIndex = parentIndex;
			parentIndex = getParentIndex(currentIndex);
		}
	}

	public int getMax() {
		return data[0];
	}

	public int extractMax() {
		int min = getMax();
		data[0] = data[--size];
		fixHeapAfterExtraction(0);
		return min;
	}

	public void fixHeapAfterExtraction(int currentIndex) {
		if(currentIndex >= size) {
			return;
		}

		while(currentIndex < size) {
			int leftChildIndex = getLeftIndex(currentIndex);
			int rightChildIndex = getRightIndex(currentIndex);

			int maxIndex = currentIndex;

			if(leftChildIndex < size && data[currentIndex] < data[leftChildIndex]) {
				maxIndex = leftChildIndex;
			}

			if(rightChildIndex < size && data[rightChildIndex] > data[maxIndex]) {
				maxIndex = rightChildIndex;
			}
			if(currentIndex == maxIndex) {
				break;
			}
			swap(currentIndex, maxIndex);
			currentIndex = maxIndex;
		}
	}
}









class MinHeap {
	int[] data;
	int size;
	int capacity;

	public MinHeap(int capacity) {
		data = new int[capacity];
		size = 0;
		this.capacity = capacity;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getLeftIndex(int currentIndex) {
		return 2*currentIndex + 1;
	}

	public int getRightIndex(int currentIndex) {
		return 2*currentIndex + 2;
	}

	public int getParentIndex(int currentIndex) {
		return (currentIndex - 1)/2;
	}

	public void printHeapLevelOrder() {
		int currentIndex = 0;
		while(currentIndex < size) {
			System.out.println(data[currentIndex++]);
		}
	}

	public void insert(int element) {
		data[size] = element;
		fixHeapAfterInsertion(size++);
	}

	public void swap(int beginIndex, int endIndex) {
		int temp = data[beginIndex];
		data[beginIndex] = data[endIndex];
		data[endIndex] = temp;
	}

	public void fixHeapAfterInsertion(int currentIndex) {
		if(currentIndex <= 0) {
			return;
		}
		int parentIndex = getParentIndex(currentIndex);
		while(currentIndex > 0 && data[parentIndex] > data[currentIndex]) {
			swap(currentIndex, parentIndex);
			currentIndex = parentIndex;
			parentIndex = getParentIndex(currentIndex);
		}
	}

	public int getMin() {
		return data[0];
	}

	public int extractMin() {
		int min = getMin();
		data[0] = data[--size];
		fixHeapAfterExtraction(0);
		return min;
	}

	public void fixHeapAfterExtraction(int currentIndex) {
		if(currentIndex >= size) {
			return;
		}

		while(currentIndex < size) {
			int leftChildIndex = getLeftIndex(currentIndex);
			int rightChildIndex = getRightIndex(currentIndex);

			int minIndex = currentIndex;

			if(leftChildIndex < size && data[currentIndex] > data[leftChildIndex]) {
				minIndex = leftChildIndex;
			}

			if(rightChildIndex < size && data[rightChildIndex] < data[minIndex]) {
				minIndex = rightChildIndex;
			}
			if(currentIndex == minIndex) {
				break;
			}
			swap(currentIndex, minIndex);
			currentIndex = minIndex;
		}
	}
}

public class HeapDriver {

	public static int[] heapSort(int[] nums) {
		int[] sorted = new int[nums.length];
		MinHeap minHeap = new MinHeap(nums.length);
		minHeap.data = nums;	
		minHeap.size = nums.length;
		for(int i = minHeap.data.length/2;i >= 0; i--) {
			minHeap.fixHeapAfterExtraction(i);
		}
		for(int i = 0;i < nums.length; i++) {
			minHeap.extractMin();
		}
		return minHeap.data;
	}

	public ArrayList<Integer> sortKSortedArray(int[] nums, int n, int k) {
		ArrayList<Integer> data = new ArrayList<Integer>();
		MinHeap minHeap = new MinHeap(n);
		int i = 0;
		for(i = 0;i <= k; i++) {
			minHeap.insert(nums[i]);
		}
		for(i = k + 1; i < n; i++) {
			data.add(minHeap.extractMin());
			minHeap.insert(nums[i]);
		}
		while(minHeap.size != 0) {
			data.add(minHeap.extractMin());
		}
		return data;
	}

	public int findKthLargest(int[] nums, int k) {
		MinHeap minHeap = new MinHeap(nums.length);
		for(int i = 0;i < nums.length; i++) {
			if(i < k) {
				minHeap.insert(nums[i]);
			} else {
				int min = minHeap.getMin();
				if(min < nums[i]) {
					minHeap.extractMin();
					minHeap.insert(nums[i]);
				}
			}
		}
		return minHeap.getMin();
	}


	public int[][] kClosest(int[][] points, int k) {
		int[][] data = new int[k][2];
		PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> {
			return getSquaredEuclideanDistanceFromOrigin(points[i2][0], points[i2][1]) - getSquaredEuclideanDistanceFromOrigin(points[i1][0], points[i1][1]);
		});

		for(int i = 0;i < points.length; i++) {
			pq.add(i);
			if (pq.size() > k) {
				pq.remove();
			}
		}
		int index = 0;
		while(!pq.isEmpty()) {
			int i = pq.poll();
			data[index][0] = points[i][0];
			data[index++][1] = points[i][1];
		}
		return data;
	}

	public static int getSquaredEuclideanDistanceFromOrigin(int x, int y) {
		return y*y + x*x;
	}


	public ListNode mergeKLists(ListNode[] lists) {
		PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> {
			return a.val - b.val;
		});
		for(ListNode head : lists) {
			if(head != null) {
				pq.add(head);	
			}
		}

		ListNode head = null, tail = null;

		while(!pq.isEmpty()) {
			ListNode minNode = pq.poll();
			ListNode newNode = new ListNode(minNode.val);
			if(head == null) {
				head = newNode;
				tail = newNode;
			} else {
				tail.next = newNode;
				tail = newNode;
			}
			if(newNode.next != null) {
				pq.add(minNode.next);
			}
		}

		return head;
	}

	public int[] topKFrequent(int[] nums, int k) {
		int[] solve = new int[k];
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for(int x : nums) {
			hm.put(x, hm.getOrDefault(x, 0) + 1);
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
			return hm.get(a) - hm.get(b);
		});

		for(int x : hm.keySet()) {
			pq.add(x);
			if(pq.size() > k) {
				pq.poll();
			}
		}

		int index = 0;
		while(!pq.isEmpty()) {
			solve[index++] = pq.poll();
		}

		return solve;
	}

	public int kthSmallest(int[][] matrix, int k) {
		int solve = -1;
		PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
			return matrix[a.matrixIndex][a.elementIndex] - matrix[b.matrixIndex][b.elementIndex];
		});
		
		for(int i = 0;i < matrix.length; i++) {
			pq.add(new Pair(i, 0));
		}
		
		while(k != 0) {
			Pair minPair = pq.poll();
			k--;
			if(k == 0) {
				solve = matrix[minPair.matrixIndex][minPair.elementIndex];
			} else {
				if((matrix[minPair.matrixIndex].length - 1) > minPair.elementIndex) {
					pq.add(new Pair(minPair.matrixIndex, minPair.elementIndex + 1));
				}
			}
		}
		
		return solve;
	}

	public static void main(String[] args) {

		int[][] matrix = {{-1}};
		
		System.out.println((new HeapDriver()).kthSmallest(matrix, 1));

	}

}
