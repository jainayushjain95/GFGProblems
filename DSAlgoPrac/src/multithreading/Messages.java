package multithreading;

import java.util.LinkedList;

public class Messages {

	public static void main(String[] args) throws InterruptedException {
		ProducerConsumer producerConsumer = new ProducerConsumer();

		Thread t1 = new Writer(producerConsumer);
		Thread t2 = new Reader(producerConsumer);
		
		t2.start();
		t1.start();
	}

}


class Writer extends Thread {

	private ProducerConsumer producerConsumer;
	
	public Writer(ProducerConsumer producerConsumer) {
		this.producerConsumer = producerConsumer;
	}
	
	@Override
	public void run() {
		try {
			producerConsumer.produceMessages();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Reader extends Thread {

	private ProducerConsumer producerConsumer;
	
	public Reader(ProducerConsumer producerConsumer) {
		this.producerConsumer = producerConsumer;
	}
	
	@Override
	public void run() {
		try {
			producerConsumer.consumeMessages();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ProducerConsumer {
	LinkedList<Integer> list = new LinkedList<Integer>();
	int capacity = 5;
	
	public void produceMessages() throws InterruptedException {
		int value = 0;
		while(true) {
			synchronized (this) {
				while(list.size() == capacity) {
					wait();
				}
				
				System.out.println("Producer producing value: " + value);
				list.add(value++);
				Thread.sleep(100);
				notifyAll();	
			}
		}
	}
	
	public void consumeMessages() throws InterruptedException {
		while(true) {
			synchronized (this) {
				while(list.size() == 0) {
					wait();
				}
				System.out.println("Consumer consuming value: " + list.get(list.size() - 1));
				list.removeLast();
				Thread.sleep(100);
				notifyAll();	
			}	
		}
	}
	
}
