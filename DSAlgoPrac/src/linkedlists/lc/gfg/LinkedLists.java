package linkedlists.lc.gfg;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class ListNodeComparator implements Comparator<ListNode> {

	@Override
	public int compare(ListNode o1, ListNode o2) {
		// TODO Auto-generated method stub
		return o1.val - o2.val;
	}
	
}

class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) { this.val = val; }
	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Node {
	int val;
	Node next;
	Node random;

	public Node(int val) {
		this.val = val;
		this.next = null;
		this.random = null;
	}
}

public class LinkedLists {

	ListNode head;
	Node head2;
	ListNode firstPointer;
	int carry = 0;
	int sum = 0;

	public int getSize() {
		int size = 0;
		ListNode temp = head;
		while(temp != null) {
			temp = temp.next;
			size++;
		}
		return size;
	}

	public void print(ListNode head) {
		while(head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}

	public ListNode deleteNNodesFromNode(ListNode node, int n) {
		ListNode nthNode = getNthNodeFromNode(node, n);
		ListNode newNode = null;
		if(nthNode != null) {
			newNode = nthNode.next;
		}
		node.next = newNode;
		return newNode;
	}

	public ListNode getNthNodeFromNode(ListNode node, int n) {
		ListNode nthNode = node;
		while(nthNode != null && n != 0) {
			nthNode = nthNode.next;
			n--;
		}
		return nthNode;
	}


	public void insertAtBegining(int val) {
		if(head == null) {
			head = new ListNode(val);
		} else {
			ListNode newNode = new ListNode(val);
			newNode.next = head;
			head = newNode;
		}
	}

	public int getDecimalValue(ListNode head) {
		int value = 0;
		if(head == null) {
			value = -1;
		} else if(head.next == null) {
			value = head.val;
		} else {
			int size = getSize();
			while(head != null) {
				value = value + (int)Math.pow(2, size - 1) * head.val; 
				head = head.next;
				size--;
			}
		}
		return value;
	}

	public ListNode deleteNodes(ListNode head, int m, int n) {
		ListNode pointer = head;
		while(true) {
			pointer = getNthNodeFromNode(pointer, m - 1);
			if(pointer == null || pointer.next == null) {
				break;
			}
			pointer = deleteNNodesFromNode(pointer, n);
		}
		return head;
	}

	public ListNode middleNode(ListNode head) {
		ListNode slow = head, fast = head;
		while(true) {
			if(fast == null || fast.next == null) {
				break;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public ListNode reverseRecursive(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode previous = reverseRecursive(head.next);
		head.next.next = head;
		head.next = null;
		return previous;
	}

	public ListNode reverseIterative(ListNode head) {
		ListNode newHead = head;
		if(!(head == null || head.next == null)) {
			ListNode prev = null, curr = head, next = null;
			while(curr != null) {
				next = curr.next;
				curr.next = prev;
				prev = curr;
				curr = next;
			}
			newHead = prev;
		}
		return newHead;
	}

	public static ListNode mergeTwoSortedLists(ListNode head1, ListNode head2) {
		ListNode head = null;
		ListNode pointer = null;

		while(head1 != null && head2 != null) {
			int data = (head1.val < head2.val) ? head1.val : head2.val;
			ListNode newNode = new ListNode(data);
			if(head == null) {
				head = newNode;
				pointer = newNode;
			} else {
				pointer.next = newNode;
				pointer = pointer.next;
			}
			if(head1.val < head2.val) {
				head1 = head1.next;
			} else {
				head2 = head2.next;
			}
		}

		while(head1 != null) {
			int data = head1.val;
			ListNode newNode = new ListNode(data);
			if(head == null) {
				head = newNode;
				pointer = newNode;
			} else {
				pointer.next = newNode;
				pointer = pointer.next;
			}
			head1 = head1.next;
		}

		while(head2 != null) {
			int data = head2.val;
			ListNode newNode = new ListNode(data);
			if(head == null) {
				head = newNode;
				pointer = newNode;
			} else {
				pointer.next = newNode;
				pointer = pointer.next;
			}
			head2 = head2.next;
		}

		return head;
	}

	public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
		ListNode head = null;
		ListNode pointer = null;
		int carry = 0, sum = 0;

		while(head1 != null && head2 != null) {
			sum = carry + head1.val + head2.val;
			carry = sum / 10;
			if(head == null) {
				head = new ListNode(sum % 10);
				pointer = head;
			} else {
				pointer.next = new ListNode(sum % 10);
				pointer = pointer.next;
			}
			head1 = head1.next;
			head2 = head2.next;
		}

		while(head1 != null) {
			sum = carry + head1.val;
			carry = sum / 10;
			if(head == null) {
				head = new ListNode(sum % 10);
				pointer = head;
			} else {
				pointer.next = new ListNode(sum % 10);
				pointer = pointer.next;
			}
			head1 = head1.next;
		}

		while(head2 != null) {
			sum = carry + head2.val;
			carry = sum / 10;
			if(head == null) {
				head = new ListNode(sum % 10);
				pointer = head;
			} else {
				pointer.next = new ListNode(sum % 10);
				pointer = pointer.next;
			}
			head2 = head2.next;
		}

		if(carry > 0) {
			pointer.next = new ListNode(carry);
		}

		return head;
	}

	public Node copyRandomList1(Node head) {
		Map<Node, Node> cloneMap = giveListClone(head);
		Node temp = head;
		while(head != null) {
			Node node = cloneMap.get(head);
			node.next = cloneMap.get(head.next);
			node.random = cloneMap.get(head.random);
			head = head.next;
		}
		return cloneMap.get(temp);
	}

	public Node copyRandomListConstantSpace(Node head) {
		Node temp = head;
		Node newHead = null;
		while(temp != null) {
			Node cloneNode = new Node(temp.val);
			if(newHead == null) {
				newHead = cloneNode;
			}
			cloneNode.next = temp.next;
			temp.next = cloneNode;
			temp = cloneNode.next;
		}

		temp = head;

		while(temp != null) {
			Node cloneNode = temp.next;
			cloneNode.random = (temp.random == null) ? null : temp.random.next;
			temp = cloneNode.next;
		}

		Node oldHead = head;
		Node solution = newHead;
		while(oldHead != null) {
			oldHead.next = oldHead.next.next;
			newHead.next = (newHead.next == null) ? null : newHead.next.next;
			oldHead = oldHead.next;
			newHead = newHead.next;
		}

		return solution;
	}

	public Map<Node, Node> giveListClone(Node head) {
		Map<Node, Node> cloneMap = new HashMap<Node, Node>();
		Node temp = head;
		while(temp != null) {
			cloneMap.put(temp, new Node(temp.val));
			temp = temp.next;
		}
		return cloneMap;
	}

	public boolean isPalindromeRecursive(ListNode head) {
		firstPointer = head;
		return isPalindromeRecursiveSolve(head);
	}

	public boolean isPalindromeRecursiveSolve(ListNode head) {
		if(head == null) {
			return true;
		}
		boolean isPalindrome = isPalindromeRecursiveSolve(head.next) && head.val == firstPointer.val;
		if(!isPalindrome) {
			return false;
		}
		firstPointer = firstPointer.next;
		return true;
	}

	public boolean isPalindrome(ListNode head) {
		if(head == null || head.next == null) {
			return true;
		}

		ListNode slow = head, fast = head;

		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode reversalNode = slow;
		ListNode tail = reverseLL(reversalNode);
		boolean isPalindrome = true;

		while(head != null && isPalindrome) {
			isPalindrome = head.val == tail.val;
			head = head.next;
			tail = tail.next;
		}

		return isPalindrome;
	}

	public ListNode reverseLL(ListNode reversalNode) {
		ListNode curr = reversalNode.next, prev = reversalNode, next = null;

		while(curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		reversalNode.next = null;
		return prev;
	}

	public static int getSizeLL(ListNode head) {
		int size = 0;
		while(head != null) {
			head = head.next;
			size++;
		}
		return size; 
	}

	public ListNode reverseLinkedListBetween(ListNode head, int left, int right) {
		int index = 1;
		ListNode leftNode = head;
		ListNode leftNodePre = null;
		ListNode rightNode = head;
		ListNode newHead = head;

		while(index < right) {
			if(index < left) {
				index++;
				leftNodePre = leftNode;
				leftNode = leftNode.next;
				rightNode = leftNode;
				continue;
			}
			if(index < right) {
				index++;
				rightNode = rightNode.next;
				continue;
			}
		}
		ListNode rightNodePost = (rightNode == null) ? null : rightNode.next;
		if(rightNode != null) {
			rightNode.next = null;
		}

		if(leftNodePre != null) {
			leftNodePre.next = null;
		}

		ListNode curr = leftNode, pre = null, next = null;
		while(curr != null) {
			next = curr.next;
			curr.next = pre;
			pre = curr;
			curr = next;
		}

		if(leftNodePre != null) {
			leftNodePre.next = pre;
		} else {
			newHead = pre;
		}

		leftNode.next = rightNodePost;
		return newHead;
	}

	public ListNode getNextKthNode(ListNode head, int k) {
		ListNode curr = head;
		int K = k - 1;
		while(K != 0 && curr != null) {
			curr = curr.next;
			K--;
		}
		return (K == 0) ? curr : null;
	}

	public ListNode reverseKGroupRecusrive(ListNode head, int k) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode kthNode = getNextKthNode(head, k);
		if(kthNode != null) {
			ListNode reverseNewHead = reverseFirstK(head, k);
			head.next = this.reverseKGroupRecusrive(kthNode.next, k);
			return reverseNewHead;
		}
		return head;
	}

	public ListNode reverseFirstK(ListNode head, int k) {
		ListNode curr = head, pre = null, next = null;
		while(k != 0) {
			next = curr.next;
			curr.next = pre;
			pre = curr;
			curr = next;
			k--;
		}
		return curr;
	}

	public ListNode reverseKGroup(ListNode head, int k) {
		if(head == null || head.next == null) {
			return head;
		}

		ListNode pre = null, curr = head, next = null, newHead = null, oldTail = null;

		while(true) {
			curr = head;
			ListNode kthNode = getNextKthNode(head, k);
			if(kthNode == null) {
				oldTail.next = curr;
				break;
			}
			ListNode postKthNode = kthNode.next;
			if(oldTail != null) {
				oldTail.next = null;
			}

			kthNode.next = null;
			curr = head;
			pre = null;
			while(curr != null) {
				next = curr.next;
				curr.next = pre;
				pre = curr;
				curr = next;
			}

			if(newHead == null) {
				newHead = pre;
			}
			if(oldTail != null) {
				oldTail.next = pre;	
			}
			oldTail = head;
			head = postKthNode;

		}

		return newHead;
	}

	public ListNode deleteNthFromLast(ListNode head, int n) {
		ListNode prev = null, curr = head, fast = head;
		while(fast != null) {
			fast = fast.next;
			if(n <= 0) {
				prev = curr;
				curr = curr.next;
			}
			n--;
		}
		if(prev == null) {
			head = head.next;
		} else {
			prev.next = curr.next;	
		}
		return head;
	}


	public Node copyRandomList(Node head) {
		Node copyHead = null;

		Node temp = head;
		while(temp != null) {
			Node clone = new Node(temp.val);
			if(copyHead == null) {
				copyHead = clone;
			}
			clone.next = temp.next;
			temp.next = clone;
			temp = clone.next;
		}

		temp = head;
		while(temp != null) {
			Node clone = temp.next;
			clone.random = (temp.random == null) ? null : temp.random.next;
			temp = temp.next;
		}

		Node oldHead = head, newHead = copyHead;

		while(oldHead != null) {
			oldHead.next = (oldHead.next == null) ? null : oldHead.next.next;
			newHead.next = (newHead.next == null) ? null : newHead.next.next;
			oldHead = oldHead.next;
			newHead = newHead.next;
		}

		return copyHead;
	}

	public ListNode reverseIterative2(ListNode head) {
		ListNode temp = head, prev = null;
		while(temp != null) {
			ListNode next = temp.next;
			temp.next = prev;
			prev = temp;
			temp = next;
		}
		return prev;
	}

	public ListNode reverseRecursive2(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode previous = reverseRecursive2(head.next);
		head.next.next = head;
		head.next = null;
		return previous;
	}

	public ListNode reverseKGroup2(ListNode head, int k) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode kthNode = getKthNode2(head, k);
		if(kthNode != null) {
			ListNode newHead = reverseKNodesFrom(head, k);
			head.next = reverseKGroup2(newHead, k);
			return kthNode;
		}
		return head;
	}

	public ListNode reverseKNodesFrom(ListNode head, int k) {
		ListNode prev = null, curr = head;
		while(k != 0) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
			k--;
		}
		return curr;
	}
	
	public ListNode getKthNode2(ListNode head, int k) {
		ListNode kthNode = head;
		while(kthNode != null && k > 1) {
			kthNode = kthNode.next;
			k--;
		}
		return kthNode;
	}
	
	public ListNode mergeKLists2(ListNode[] lists) {
		ListNode head = null, tail = null;
		PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(new ListNodeComparator());
		for(ListNode node : lists) {
			pq.add(node);
		}
		
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
			if(minNode.next != null) {
				pq.add(minNode.next);
			}
		}
		return head;
    }
	
	
	
	public static void main(String[] args) {

		LinkedLists linkedLists1 = new LinkedLists();
		linkedLists1.insertAtBegining(5);
		linkedLists1.insertAtBegining(4);
		linkedLists1.insertAtBegining(3);
		linkedLists1.insertAtBegining(2);
		linkedLists1.insertAtBegining(1);
		linkedLists1.print(linkedLists1.reverseKGroup2(linkedLists1.head, 3));
	}

	public ListNode addTwoNumbersAdbobe(ListNode head1, ListNode head2) {
		ListNode head = null, tail = null;

		int sum = 0, carry = 0;

		while(head1 != null && head2 != null) {
			sum = carry + head1.val + head2.val;
			carry = sum / 10;
			ListNode node = new ListNode(sum % 10);
			if(head == null) {
				head = node;
				tail = node;
			} else {
				tail.next = node;
				tail = node;
			}
			head1 = head1.next;
			head2 = head2.next;
		}

		while(head1 != null) {
			sum = carry + head1.val;
			carry = sum / 10;
			ListNode node = new ListNode(sum % 10);
			if(head == null) {
				head = node;
				tail = node;
			} else {
				tail.next = node;
				tail = node;
			}
			head1 = head1.next;
		}

		while(head2 != null) {
			sum = carry + head2.val;
			carry = sum / 10;
			ListNode node = new ListNode(sum % 10);
			if(head == null) {
				head = node;
				tail = node;
			} else {
				tail.next = node;
				tail = node;
			}
			head2 = head2.next;
		}

		if(carry != 0) {
			ListNode node = new ListNode(carry);
			tail.next = node;
		}

		return head;
	}


	public Node getRandomListInput() {
		Node node1 = new Node(7);
		Node node2 = new Node(13);
		Node node3 = new Node(11);
		Node node4 = new Node(10);
		Node node5 = new Node(1);

		getRandomListInputData(node1, node2, null);
		getRandomListInputData(node2, node3, node1);
		getRandomListInputData(node3, node4, node5);
		getRandomListInputData(node4, node5, node3);
		getRandomListInputData(node5, null, node1);

		return node1;
	}

	public void getRandomListInputData(Node node, Node next, Node random) {
		node.next= next;
		node.random = random;
	}
}
