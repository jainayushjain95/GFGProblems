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

	public Node linkedListReversalIterative() {
		Node current = head, prev = null, next = null;
		while(current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		return prev;
	}
	
	public Node linkedListReversalRecursiveOne(Node head) {
		if(head == null || head.next == null) {
			return head;
		}
		Node smallerHead = linkedListReversalRecursiveOne(head.next);
		head.next.next = head;
		head.next = null;
		return smallerHead;
	}

	public Node linkedListReversalRecursiveTwo(Node current, Node previous) {
		if(current == null) {
			return previous;
		}
		Node next = current.next;
		current.next = previous;
		return linkedListReversalRecursiveTwo(next, current);
	}

	/*
	 * reverse a linked list in groups of size k
	 */

	public Node reverseLinkedListInGroups(Node head, int k) {
		Node current = head, prev = null, next = null;
		int count = k;
		while(count != 0 && current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			count--;
		}
		if(current != null) {
			Node tempHead = reverseLinkedListInGroups(current, k);
			head.next = tempHead;
		}
		return prev;
	}

	
	public Node segrateOddsAndEvens() {
		Node evenStart = null, evenEnd = null, oddStart = null, oddEnd = null;
		Node temp = head;
		Node output = null;
		while(temp != null) {
			int data = temp.data;
			if(data % 2 == 0) {
				if(evenStart == null) {
					evenStart = temp;
					evenEnd = evenStart;
				} else {
					evenEnd.next = temp;
					evenEnd = temp;
				}
			} else {
				if(oddStart == null) {
					oddStart = temp;
					oddEnd = oddStart;
				} else {
					oddEnd.next = temp;
					oddEnd = temp;
				}
			}
			temp = temp.next;
		}
		if(evenStart == null || oddStart == null) {
			output = head;
		} else {
			evenEnd.next = oddStart;
			oddEnd.next = null;
			output = evenStart;
		}
		return output;
	}
	
	
	public Node mergeTwoSortedLinkedListsInPlace(Node firstHead, Node secondHead) {
		Node temp1 = firstHead, temp2 = secondHead;
		Node outputHead = null;
		if(temp1 == null) {
			return temp2;
		} else if(temp2 == null) {
			return temp1;
		}
		if(temp1.data < temp2.data) {
			outputHead = temp1;
		} else {
			outputHead = temp2;
		}
		while(temp1 != null && temp2 != null) {
			if(temp1.data < temp2.data) {
				while(temp1.next != null && temp1.next.data < temp2.data) {
					temp1 = temp1.next;
				}
				Node temp = temp1.next;
				temp1.next = temp2;
				temp1 = temp;
			} else {
				while(temp2.next != null && temp2.next.data < temp1.data) {
					temp2 = temp2.next;
				}
				Node temp = temp2.next;
				temp2.next = temp1;
				temp2 = temp;
			}
			
		}
		
		return outputHead;
	}
	
	public void printLinkedList() {
		Node temp = head;
		while(temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
	}

	public void printLinkedList(Node head) {
		Node temp = head;
		while(temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
	}

}
