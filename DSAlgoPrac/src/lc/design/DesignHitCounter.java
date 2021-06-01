package lc.design;

import java.util.LinkedList;
import java.util.Queue;

public class DesignHitCounter {

	public static void main(String[] args) {
		HitCounter hitCounter = new HitCounter();
		hitCounter.hit(1);
		hitCounter.hit(2);
		hitCounter.hit(3);
		System.out.println(hitCounter.getHits(4));
		hitCounter.hit(300);
		System.out.println(hitCounter.getHits(300));
		System.out.println(hitCounter.getHits(301));
	}
}

class HitCounter {

	Queue<Integer> queue;
	public static int PAST_TIMESTAMP_FRAME = 300;
	
    /** Initialize your data structure here. */
    public HitCounter() {
    	queue = new LinkedList<Integer>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        queue.add(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
    	while(!queue.isEmpty()) {
    		int diff = timestamp - PAST_TIMESTAMP_FRAME;
    		if(diff >= queue.peek()) {
    			queue.poll();
    		} else {
    			break;
    		}
    	}
    	return queue.size();
    }
}
