package heap;

public class HeapDriver {
    public static void main(String[] args) {
        int[] arr = {10, 6, 7, 5};
        MaxHeap maxHeap = new MaxHeap(arr);
        maxHeap.heapSort(arr);
        for(int x : arr) {
            System.out.print(x + " ");
        }
    }
}
