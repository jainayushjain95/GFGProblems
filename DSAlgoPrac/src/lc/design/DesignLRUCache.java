package lc.design;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DesignLRUCache {

	public static void main(String[] args) {
		LRUCache lruCache = new LRUCache(2);
		lruCache.put(2, 1);
		lruCache.put(2, 2);
		System.out.println(lruCache.get(2));
		lruCache.put(1, 1);
		lruCache.put(4, 1);
		System.out.println(lruCache.get(2));
//		System.out.println(lruCache.get(2));
	}

}

class ListNode {
	int val;
	int key;
	ListNode left;
	ListNode right;
	public ListNode(int val, int key) {
		super();
		this.val = val;
		this.key = key;
	}
	
	
}

class DoublyLL {
	ListNode head;
	ListNode tail;
	int size;
	public DoublyLL(int size) {
		super();
		this.size = size;
	}
}

class LRUCache {

	ListNode[] map;
	DoublyLL doublyLL;
	int capacity;
	
    public LRUCache(int capacity) {
        map = new ListNode[10001];
        Arrays.fill(map, null);
        this.capacity = capacity;
        doublyLL = new DoublyLL(0);
    }
    
    public int get(int key) {
        int value = -1;
        
        if(map[key] != null) {
        	ListNode node = map[key];
        	value = node.val;	
        	removeNodeFromDLL(node);
        	
        	doublyLL.size--;
        	addNodeToFrontOfDLL(node);
        	
        	doublyLL.size++;
        }
    	return value;
    }
    
    public void put(int key, int value) {
    	ListNode node = map[key];
    	if(node != null) {
    		node.val = value;
    		removeNodeFromDLL(node);
    		doublyLL.size--;
    		addNodeToFrontOfDLL(node);	
    		doublyLL.size++;
    	} else if(doublyLL.size < capacity){
    		node = new ListNode(value, key);
        	addNodeToFrontOfDLL(node);
        	doublyLL.size++;
        	map[key] = node;
    	} else {
    		node = new ListNode(value, key);
    		map[doublyLL.tail.key] = null;
    		removeNodeFromDLL(doublyLL.tail);
    		doublyLL.size--;
    		map[key] = node;
    		addNodeToFrontOfDLL(node);
        	doublyLL.size++;
    	}
    }
    
    public void addNodeToFrontOfDLL(ListNode node) {
    	if(doublyLL.size == 0) {
    		doublyLL.head = node;
    		doublyLL.tail = node;
    	} else {
    		node.right = doublyLL.head;
    		doublyLL.head.left = node;
    		doublyLL.head = node;
    	}
    }
    
    public void removeNodeFromDLL(ListNode node) {
    	if(doublyLL.head == node) {
    		if(doublyLL.size == 1) {
    			doublyLL.head = null;
    			doublyLL.tail = null;
    		} else {
    			doublyLL.head = doublyLL.head.right;
        		node.right = null;
        		doublyLL.head.left = null;	
    		}
    	} else if(doublyLL.tail == node) {
    		if(doublyLL.size == 1) {
    			doublyLL.head = null;
    			doublyLL.tail = null;
    		} else {
    			doublyLL.tail = doublyLL.tail.left;
    			node.left = null;
        		doublyLL.tail.right = null;
    		}
    	} else {
    		node.left.right = node.right;
        	node.right.left = node.left;
        	node.left = null;
        	node.right = null;	
    	}
    }
}