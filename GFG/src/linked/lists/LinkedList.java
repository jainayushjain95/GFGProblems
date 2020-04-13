package linked.lists;

public class LinkedList {

	Node head;
	int sizeOfLinkedList;
	
	public LinkedList() {
		sizeOfLinkedList = 0;
	}
	
	public void insertAtBegining(int data) {
		Node newNode = new Node(data);
		newNode.next = head;
		head = newNode;
		sizeOfLinkedList++;
	}
	
	public void insertAtTheEnd(int data) {
		Node newNode = new Node(data);
		Node temp = head;
		if(temp == null) {
			head = newNode;
		} else {
			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = newNode;
		}
		
		sizeOfLinkedList++;
	}
	
	public int findTheMiddleOfLinkedList() {
		int midData = -1;
		Node slow = head;
		Node fast = head;
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		if(slow != null) {
			midData = slow.data;
		}
		return midData;
	}
	
	public int findNthNodeFromLinkedList(int n) {
		int nthNodeDataFromEnd = -1;
		Node first = head, second = head;
		while(first != null && n != 0) {
			n--;
			first = first.next;
		}
		while(first != null) {
			first = first.next;
			second = second.next;
		}
		if(n == 0 && second != null) {
			nthNodeDataFromEnd = second.data;
		}
		return nthNodeDataFromEnd;
	}
	
	public void printLinkedList() {
		Node temp = head;
		while(temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
	}
	
}
