package multithreading;

public class AnotherThread extends Thread {

	@Override
	public void run() {
		try {
			System.out.println("Another thread Going to sleep...");
			Thread.sleep(3000);
			System.out.println("Another thread woke up...");
		} catch(InterruptedException e) {
			System.out.println("Another thread Going to sleep prematurely...");
		}
	}
	
}
