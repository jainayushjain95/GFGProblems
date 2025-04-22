package design;

import java.util.*;

class DDNode_aio {
    DDNode_aio prev;
    DDNode_aio next;
    int frequency;
    Set<String> keys;

    public DDNode_aio(int frequency) {
        this.keys = new HashSet<>();
        this.frequency = frequency;
    }
}

public class AllInOne {

    DDNode_aio head;
    DDNode_aio tail;
    Map<String, DDNode_aio> map;

    public AllInOne() {
        head = new DDNode_aio(0);
        tail = new DDNode_aio(0);
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        if(!map.containsKey(key)) {
            if(head.next.frequency != 1) {
                addNodeAfter(head, 1);
            }
            head.next.keys.add(key);
            map.put(key, head.next);
        } else {
            DDNode_aio currNode = map.get(key);
            int currFreq = currNode.frequency;
            if(currNode.next.frequency != currFreq + 1) {
                addNodeAfter(currNode, currFreq + 1);
            }
            currNode.next.keys.add(key);
            currNode.keys.remove(key);
            map.put(key, currNode.next);
            if(currNode.keys.isEmpty()) {
                deleteThisNode(currNode);
            }
        }
    }

    public void dec(String key) {
        DDNode_aio currNode = map.get(key);
        int currFreq = currNode.frequency;
        if(currFreq == 1) {
            map.remove(key);
        } else {
            if(currNode.prev.frequency != currFreq - 1) {
                addNodeAfter(currNode.prev, currFreq - 1);
            }
            currNode.prev.keys.add(key);
            map.put(key, currNode.prev);
        }
        currNode.keys.remove(key);

        if(currNode.keys.isEmpty()) {
            deleteThisNode(currNode);
        }
    }

    public String getMaxKey() {
        return (!map.isEmpty()) ? tail.prev.keys.iterator().next() : "";
    }

    public String getMinKey() {
        return (!map.isEmpty()) ? head.next.keys.iterator().next() : "";
    }

    private void addNodeAfter(DDNode_aio node, int frequency) {
        DDNode_aio newNode = new DDNode_aio(frequency);
        newNode.next = node.next;
        node.next.prev = newNode;
        node.next = newNode;
        newNode.prev = node;
    }

    private void deleteThisNode(DDNode_aio node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    public static void main(String[] args) {
        AllInOne allInOne = new AllInOne();
        allInOne.inc("a");
        allInOne.inc("a");
        System.out.println(allInOne.getMaxKey());
        System.out.println(allInOne.getMinKey());
        allInOne.dec("a");
        allInOne.dec("a");
        System.out.println(allInOne.getMaxKey());
        allInOne.inc("a");
        System.out.println(allInOne.getMinKey());
    }
}
