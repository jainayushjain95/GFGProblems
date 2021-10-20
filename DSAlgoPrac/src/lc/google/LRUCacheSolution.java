package lc.google;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class LRUCacheSolution {
	public static void main(String[] args) {
		LRUCache lruCache = new LRUCache(10);
		lruCache.put(1, 30);
		System.out.println(lruCache.get(11));
	}
}


class LRUCache {

	LinkedList1 linkedList;
	Node[] keysMap;
	int totalCapacity;

	public LRUCache(int capacity) {
		this.totalCapacity = capacity;
		keysMap = new Node[20];
		linkedList = new LinkedList1(null, null, 0);
	}

	public int get(int key) {
		int item = -1;
		if(keysMap[key] != null) {
			item = keysMap[key].value;
			linkedList.removeFromList(keysMap[key]);
			linkedList.addToFront(new Node(key, item, null, null));
		}
		return item;
	}

	public void put(int key, int value) {
		Node node = keysMap[key];
		if(node != null) {
			linkedList.removeFromList(node);
			node = new Node(key, value, null, null);
			linkedList.addToFront(node);
		} else if(linkedList.size == totalCapacity){
			keysMap[linkedList.tail.key] = null;
			linkedList.removeFromBack();
			node = new Node(key, value, null, null);
			linkedList.addToFront(node);
		} else {
			node = new Node(key, value, null, null);
			linkedList.addToFront(node);
		}

		keysMap[key] = node;
	}
}

class LinkedList1 {
	Node head;
	Node tail;
	int size;

	public LinkedList1(Node head, Node tail, int size) {
		super();
		this.head = head;
		this.tail = tail;
		size = 0;
	}

	public void addToFront(Node node) {
		if(head == null) {
			head = node;
			tail = head;
		} else {
			node.right = head;
			head.left = node;
			head = node;
		}
		size++;
	}

	public void removeFromList(Node node) {
		if(tail == null) {
			System.out.println("AAA");
		}
		Node previous = node.left;
		Node next = node.right;

		if(previous == null && next == null) {
			head = null;
			tail = null;
		} else if(previous == null) {
			head = head.right;
			next.left = null;
		} else if(next == null) {
			tail = tail.left;
			previous.right = null;
		} else {
			previous.right = next;
			next.left = previous;	
		}
		size--;
	}

	public void removeFromBack() {
		removeFromList(tail);
	}
}

class Node {

	int key;
	int value;
	Node left;
	Node right;

	public Node() {

	}

	public Node(int key, int value, Node left, Node right) {
		super();
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

}