package linked.lists;

public class LinkedListDriver {

	public static void main(String[] args) {
//		LinkedList linkedList = new LinkedList();
//		for(int i = 2; i <= 10; i = i + 2) {
//			linkedList.insertAtTheEnd(i);
//		}
//		Node newHead = linkedList.segrateOddsAndEvens();
//		linkedList.printLinkedList(newHead);
		LinkedList ll1 = new LinkedList();
		for(int i = 1;i <= 5; i++) {
			ll1.insertAtTheEnd(i);
		}
		
		LinkedList ll2 = new LinkedList();
		for(int i = 10;i <= 15; i++) {
			ll2.insertAtTheEnd(i);
		}
		
		Node mergedLL = ll1.mergeTwoSortedLinkedListsInPlace(ll1.head, ll2.head);
		ll1.printLinkedList(mergedLL);
	}

}
