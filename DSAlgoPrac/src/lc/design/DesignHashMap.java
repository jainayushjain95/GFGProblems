package lc.design;

import java.util.LinkedList;
import java.util.List;

public class DesignHashMap {

	public static void main(String[] args) {
		MyHashMap myHashMap = new MyHashMap();
		myHashMap.put(1, 10);
		myHashMap.put(2, 103);
		System.out.println(myHashMap.get(2));
	}
}


class Pair {
	int key;
	int value;
	public Pair(int key, int value) {
		super();
		this.key = key;
		this.value = value;
	}
}

class Bucket {
	List<Pair> pairs;

	public Bucket() {
		super();
		pairs = new LinkedList<Pair>();
	}

	public void put(int key, int value) {
		boolean isFound = false;
		for(Pair pair : pairs) {
			if(pair.key == key) {
				isFound = true;
				pair.value = value;
			}
		}
		if(!isFound) {
			pairs.add(new Pair(key, value));
		}
	}

	public void remove(int key) {
		Pair pairToRemove = null;
		for(Pair pair : pairs) {
			if(pair.key == key) {
				pairToRemove = pair;
				break;
			}
		}
		if(pairToRemove != null) {
			pairs.remove(pairToRemove);
		}
	}

	public int get(int key) {
		for(Pair pair : pairs) {
			if(pair.key == key) {
				return pair.value;
			}
		}
		return -1;
	}
}

class MyHashMap {

	Bucket[] buckets;
	int bucketSize;

	public MyHashMap() {
		this.bucketSize = 100;
		buckets = new Bucket[bucketSize];
	}

	/** value will always be non-negative. */
	public void put(int key, int value) {
		int index = key % bucketSize;
		if(buckets[index] == null) {
			buckets[index] = new Bucket();
		}
		buckets[index].put(key, value);
	}

	/** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
	public int get(int key) {
		int index = key % bucketSize;
		if(buckets[index] == null) {
			buckets[index] = new Bucket();
		}
		return buckets[index].get(key);
	}

	/** Removes the mapping of the specified value key if this map contains a mapping for the key */
	public void remove(int key) {
		int index = key % bucketSize;
		if(buckets[index] != null) {
			buckets[index].remove(key);
		}
	}
}
