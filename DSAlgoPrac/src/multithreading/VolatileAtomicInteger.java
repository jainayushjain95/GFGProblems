package multithreading;

public class VolatileAtomicInteger {

	public static void main(String[] args) {
		Operation operation = new Operation();
		operation.flag = true;
		
		ThreadClass1 threadClass1 = new ThreadClass1(operation);
		ThreadClass2 threadClass2 = new ThreadClass2(operation);
	
		threadClass1.start();
		threadClass2.start();
	}

}


class ThreadClass1 extends Thread {

	private Operation operation;
	public ThreadClass1(Operation operation) {
		super();
		this.operation = operation;
	}


	@Override
	public void run() {
		operation.loop();
	}
	
}

class ThreadClass2 extends Thread {

	private Operation operation;
	public ThreadClass2(Operation operation) {
		super();
		this.operation = operation;
	}


	@Override
	public void run() {
		operation.set();
	}
	
}

class Operation {
	boolean flag;
	
	public void loop() {
		while(flag) {
			System.out.println("A");
		}
	}
	
	public void set() {
		flag = false;
	}
	
}