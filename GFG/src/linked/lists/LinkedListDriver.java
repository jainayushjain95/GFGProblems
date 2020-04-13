package linked.lists;

public class LinkedListDriver {

	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList();
		for(int i = 10; i <= 60; i = i + 10) {
			linkedList.insertAtTheEnd(i);
		}
		System.out.println(linkedList.findNthNodeFromLinkedList(22));
	}

}
