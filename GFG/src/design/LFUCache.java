package design;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    private DoublyLinkedListNode head;
    private DoublyLinkedListNode tail;
    private int capacity;
    private Map<Integer, DoublyLinkedListNode> map;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new DoublyLinkedListNode(-1, -1);
        this.tail = new DoublyLinkedListNode(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        int value = -1;
        if (map.containsKey(key)) {
            DoublyLinkedListNode node = map.get(key);
            value = node.val;
            deleteNode(node);
            map.put(key, insertNodeAtFront(key, value));
        }
        return value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DoublyLinkedListNode node = map.get(key);
            deleteNode(node);
            map.put(key, insertNodeAtFront(key, value));
        } else if(capacity <= map.size()) {
            DoublyLinkedListNode lruNode = tail.prev;
            deleteNode(lruNode);
            map.remove(lruNode.key);
            map.put(key, insertNodeAtFront(key, value));
        } else {
            map.put(key, insertNodeAtFront(key, value));
        }
    }

    private void deleteNode(DoublyLinkedListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    private DoublyLinkedListNode insertNodeAtFront(int key, int val) {
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(key, val);
        DoublyLinkedListNode node = head.next;
        head.next = newNode;
        newNode.prev = head;
        newNode.next = node;
        node.prev = newNode;
        return newNode;
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(2, 1);
//        cache.put(1, 1);
        System.out.println(cache.get(2));
        System.out.println(cache.get(2));
//
//        cache.put(4, 1);
//        System.out.println(cache.get(2));

//        cache.put(3, 3);
//        System.out.println(cache.get(2));
//
//        cache.put(4, 4);
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(4));
    }
}
