package lc.design;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import lc.arrays.Probs;

public class DesignMedianFinder {
	public static void main(String[] args) {
		MedianFinder medianFinder = new MedianFinder();
		
		medianFinder.addNum(41);
		System.out.println(medianFinder.findMedian());
		
		medianFinder.addNum(35);
		System.out.println(medianFinder.findMedian());
		
		medianFinder.addNum(62);
		System.out.println(medianFinder.findMedian());
		
		medianFinder.addNum(5);
		System.out.println(medianFinder.findMedian());
		
		medianFinder.addNum(97);
		System.out.println(medianFinder.findMedian());
		
		medianFinder.addNum(108);
		System.out.println(medianFinder.findMedian());
		
	}
}


class MaxHeapCompare implements Comparator<Integer> {
	public int compare(Integer o1, Integer o2) {
		return o2 - o1;
	}
	
}

class MedianFinder {
	
	PriorityQueue<Integer> minHeap;
	PriorityQueue<Integer> maxHeap;
	
    public MedianFinder() {
    	minHeap = new PriorityQueue<Integer>();
    	maxHeap = new PriorityQueue<Integer>(new MaxHeapCompare());
    }
    
    public void addNum(int num) {
    	minHeap.add(num);
    	maxHeap.add(minHeap.poll());
    	
    	rebalance();
    }
    
    private void rebalance() {
    	if(maxHeap.size() > minHeap.size()) {
    		minHeap.add(maxHeap.poll());
    	}
    }
    
    public double findMedian() {
    	if(minHeap.size() == maxHeap.size()) {
    		return ((double)minHeap.peek() + (double)maxHeap.peek())/2;
    	} else {
    		return (double)minHeap.peek();
    	}
    }
}
