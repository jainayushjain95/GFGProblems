package multithreading;

public class ThreadInterrupts {

	public static void main(String[] args) throws InterruptedException {
		InterruptExample.example();
	}

}


class InterruptExample {
	static public void example() throws InterruptedException {
		final Thread sleepyThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("A: " + Thread.currentThread().isInterrupted());
					System.out.println("Going to sleep....");
					Thread.sleep(100000000);
					System.out.println("B: " + Thread.currentThread().isInterrupted());
				} catch(InterruptedException e) {
					System.out.println("C: " + Thread.currentThread().isInterrupted());
					Thread.currentThread().interrupt();
					System.out.println("Woke up...");
					System.out.println("D: " + Thread.currentThread().isInterrupted());
					System.out.println("E: " + Thread.currentThread().isInterrupted());
				}
			}
		});
		
		sleepyThread.start();
		Thread.sleep(1000);
		System.out.println("F: " + sleepyThread.isInterrupted());
		System.out.println("About to woke up the thread......");
		sleepyThread.interrupt();
		System.out.println("G: " + sleepyThread.isInterrupted());
		System.out.println("Woke up the thread......");
		sleepyThread.join();
	}
}
