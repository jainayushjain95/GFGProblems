package binary.heaps;

public class MinHeapDriver {

	public static void main(String[] args) {
		MinHeap minHeap = new MinHeap(100);
		minHeap.insert(10);
		minHeap.insert(20);
		minHeap.insert(30);
		minHeap.insert(40);
		minHeap.insert(50);
		minHeap.insert(35);
		minHeap.insert(38);
		minHeap.insert(45);
		
		minHeap.printMinHeap();
	
		System.out.println("\n");
		
		minHeap.deleteKeyAtIndex(3);
		
		minHeap.printMinHeap();
	}

}
