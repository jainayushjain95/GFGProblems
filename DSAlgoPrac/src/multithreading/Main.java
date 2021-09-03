package multithreading;

public class Main {

	public static void main(String[] args) {
		System.out.println("Main Thread...");
		
		Thread currentThread = Thread.currentThread();
		
		Thread anotherThread = new AnotherThread();
		anotherThread.start();
		try {
			anotherThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main Thread 2...");
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	

}
