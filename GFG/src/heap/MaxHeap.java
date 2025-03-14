package heap;

public class MaxHeap {
    int[] maxHeap;
    int size;

    public MaxHeap(int[] arr) {
        this.maxHeap = arr;
        size = arr.length;
    }

    public int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    public int getRightChildIndex(int i) {
        return 2 * i + 2;
    }

    public int getParentIndex(int i) {
        return (i - 1)/2;
    }

    public int getIndexOfRightMostInternalNode() {
        return getParentIndex(size - 1);
    }

    private void swap(int i, int j) {
        int temp = maxHeap[i];
        maxHeap[i] = maxHeap[j];
        maxHeap[j] = temp;
    }

    public void buildMaxHeap() {
        int endIndex = getIndexOfRightMostInternalNode();
        for (int i = endIndex; i >= 0; i--) {
            heapify(i);
        }
    }

    public void heapify(int i) {
        int leftChildIndex = getLeftChildIndex(i);
        int rightChildIndex = getRightChildIndex(i);


        int maxIndex = i;

        maxIndex = (leftChildIndex < size && maxHeap[maxIndex] < maxHeap[leftChildIndex]) ? leftChildIndex : maxIndex;
        maxIndex = (rightChildIndex < size && maxHeap[maxIndex] < maxHeap[rightChildIndex]) ? rightChildIndex : maxIndex;


        if(i != maxIndex) {
            swap(i, maxIndex);
            heapify(maxIndex);
        }
    }

    public void extractMax(int i) {
        swap(i, 0);
        size--;
        heapify(0);
    }

    public void heapSort(int[] arr) {
        buildMaxHeap();
        for (int i = arr.length - 1; i > 0; i--) {
            extractMax(i);
        }
    }
}
