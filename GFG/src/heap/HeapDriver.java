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

/*
insert(10)
insert(20)
insert(15)
insert(30)
insert(25)
remove() ⬅️ (Removes max, should be 30)
insert(40)
insert(35)
remove() ⬅️ (Removes max, should be 40)
insert(50)
insert(45)
remove() ⬅️ (Removes max, should be 50)
remove() ⬅️ (Removes max, should be 45)
insert(55)
insert(60)
insert(65)
remove() ⬅️ (Removes max, should be 65)
remove() ⬅️ (Removes max, should be 60)
remove() ⬅️ (Removes max, should be 55)
insert(70)
 */
