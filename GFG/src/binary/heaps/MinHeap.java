package binary.heaps;

public class MinHeap {

	int[] minHeap;
	int capacity;
	int size;

	public MinHeap(int capacity) {
		minHeap = new int[capacity];
		this.capacity= capacity;
		size = 0;
	}

	public int getLeftChildIndex(int i) {
		return 2*i + 1;
	}

	public int getRighttChildIndex(int i) {
		return 2*i + 2;
	}

	public int getParentIndex(int i) {
		return (i - 1) / 2;
	}
	
	public void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;  
	}
	
	public int getBottomMostRightMostInternalNodeIndex() {
		int index = size - 1;
		index = getParentIndex(index);
		return index;
	}

	public void insert(int element) {
		
		if(size >= capacity) {
			return;
		}
		
		minHeap[size++] = element;
		int currentIndex = size - 1;
		int parentIndex = getParentIndex(currentIndex);
		
		while(parentIndex >= 0 && minHeap[currentIndex] < minHeap[parentIndex]) {
			swap(minHeap, currentIndex, parentIndex);
			currentIndex = parentIndex;
			parentIndex = getParentIndex(currentIndex);
		}
	}
	
	public void printMinHeap() {
		for(int i = 0; i < size; i++) {
			int leftChild = getLeftChildIndex(i);
			int rightChild = getRighttChildIndex(i);
			System.out.print("Node: " + minHeap[i]);
			if(leftChild < size) {
				System.out.print(", Leftchild: " + minHeap[leftChild]);
			}
			
			if(rightChild < size) {
				System.out.print(", Rightchild: " + minHeap[rightChild]);
			}
			
			System.out.println();
		}
	}

	//Index: where violation of min heap ppt is supposed to be violated
	public void minHeapify(int index) {
		int leftIndex = getLeftChildIndex(index);
		int rightIndex = getRighttChildIndex(index);
		
		int smallest = index;
		smallest = (leftIndex < size && minHeap[smallest] > minHeap[leftIndex]) ? leftIndex : smallest; 
		smallest = (rightIndex < size && minHeap[smallest] > minHeap[rightIndex]) ? rightIndex : smallest;
		
		if(smallest != index) {
			swap(minHeap, smallest, index);
			minHeapify(smallest);
		}
	}
	
	
	/*
	 * Retrieves minimum + deletes that minimum from heap + min heapify again
	 */
	public int extractMin() {
		if(size == 0) {
			return Integer.MAX_VALUE;
		} else if(size == 1) {
			size--;
			return minHeap[0];
		}
		int minimumElement = minHeap[0];
		minHeap[0] = minHeap[size - 1];
		size--;
		minHeapify(0);
		return minimumElement;
	}
	
	
	public void deleteKeyAtIndex(int index) {
		minHeap[index] = Integer.MIN_VALUE;
		int parentIndex = getParentIndex(index);
		while(parentIndex >= 0 && minHeap[index] < minHeap[parentIndex]) {
			swap(minHeap, index, parentIndex);
			index = parentIndex;
			parentIndex = getParentIndex(index);
		}
		extractMin();
	}
	
	public void buildHeap(int[] array) {
		int startingIndex = getBottomMostRightMostInternalNodeIndex();
		for(int i = startingIndex; i >= 0; i--) {
			minHeapify(i);
		}
	}
	
}
