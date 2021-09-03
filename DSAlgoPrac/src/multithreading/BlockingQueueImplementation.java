package multithreading;

public class BlockingQueueImplementation {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue blockingQueue = new BlockingQueue(2);
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 1;i <= 6; i++) {
					try {
						blockingQueue.enqueue(i);
						System.out.println("Enqueued: " + i);
					} catch (InterruptedException e) {
						System.out.println("Exception in t1: " + e.getMessage());
					}
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 1;i <= 4; i++) {
					try {
						System.out.println("Dequeued: " + blockingQueue.dequeue());
					} catch (InterruptedException e) {
						System.out.println("Exception in t2: " + e.getMessage());
					}
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}

}

class BlockingQueue {
	Integer[] queue;
	int size;
	int capacity;
	int head;
	int tail;

	
	public BlockingQueue(int capacity) {
		super();
		this.capacity = capacity;
		this.size = 0;
		this.head = 0;
		this.tail = 0;
		queue =  new Integer[capacity];
	}
	
	public synchronized void enqueue(Integer element) throws InterruptedException {
		System.out.println("A");
		while(size == capacity) {
			System.out.println("A waiting.....");
			wait();
		}
		
		if(tail == capacity) {
			tail = 0;
		}
		
		queue[tail++] = element;
		size++;
		notifyAll();
	}
	
	public synchronized Integer dequeue() throws InterruptedException {
		System.out.println("B");
		while(size == 0) {
			System.out.println("B waiting.....");
			wait();
		}
		if(head == capacity) {
			head = 0;
		}
		
		Integer element = queue[head];
		queue[head++] = null;
		size--;
		notifyAll();
		return element;
	}
}
