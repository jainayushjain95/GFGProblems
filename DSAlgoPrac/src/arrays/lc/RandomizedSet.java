package arrays.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomizedSet {

	List<Integer> list;
	Map<Integer, Integer> hm;
	int size;

	public RandomizedSet() {
		list = new ArrayList<Integer>();
		hm = new HashMap<Integer, Integer>();
		size = 0;
	}

	public boolean isExists(int val) {
		return hm.containsKey(val);
	}

	public void swap(int i, int j) {
		int temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
	
	public boolean insert(int val) {
		boolean flag = isExists(val);
		if(!flag) {
			hm.put(val, list.size());
			list.add(val);
			size++;
		}
		return !flag;
	}

	public boolean remove(int val) {
		boolean flag = isExists(val);
		if(flag) {
			int index = hm.get(val);
			hm.remove(val);
			if(hm.containsKey(list.get(size - 1))) {
				hm.put(list.get(size - 1), index);	
			}
			swap(index, size - 1);
			list.remove(list.size() - 1);
			size = list.size();
		}
		return flag;
	}

	public int getRandom() {
		int right = list.size();
		int rand = (int)(Math.random()*right);
		System.out.println("rand: " + rand);
		return list.get(rand);
	}
	
	public static void main(String[] args) {
		RandomizedSet randomizedSet = new RandomizedSet();
		System.out.println(randomizedSet.insert(1));
		System.out.println(randomizedSet.insert(10));
		System.out.println(randomizedSet.insert(20));
		System.out.println(randomizedSet.insert(30));
		System.out.println(randomizedSet.getRandom());
		System.out.println(randomizedSet.getRandom());
		System.out.println(randomizedSet.getRandom());
		System.out.println(randomizedSet.getRandom());
		System.out.println(randomizedSet.getRandom());
		System.out.println(randomizedSet.getRandom());
		System.out.println(randomizedSet.getRandom());
		System.out.println(randomizedSet.getRandom());
		
	}

}

