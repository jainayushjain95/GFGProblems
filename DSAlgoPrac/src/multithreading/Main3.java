package multithreading;

public class Main3 {

	public static void main(String[] args) throws InterruptedException {
		IncorrectSync.runEx();
	}

}


class IncorrectSync {
	Boolean flag = new Boolean(true);
	
	public static void runEx() throws InterruptedException {
		IncorrectSync incorrectSync = new IncorrectSync();
		incorrectSync.example();
	}
	
	public void example() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (flag) {
					try {
						while(flag) {
							System.out.println("A1: " + Thread.holdsLock(flag));
							System.out.println("First thread about to sleep...");
							System.out.println("B1: " + Thread.holdsLock(flag));
							Thread.sleep(5000);
							System.out.println("C1: " + Thread.holdsLock(flag));
							System.out.println("Woke up thread about to call wait...");
							System.out.println("D1: " + Thread.holdsLock(flag));
							flag.wait();
						}
					} catch(Exception e) {
						System.out.println("1 Exception");
					}
				}
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("XXXXXXX");
					synchronized (flag) {
						System.out.println("--");
						System.out.println("A2: " + Thread.holdsLock(flag));
						flag = false;
						System.out.println("B2: " + Thread.holdsLock(flag));	
					}	
				} catch(Exception e) {
					System.out.println("2 Exception");	
				}
			}
			
		});
		
		try {
			t1.start();
			Thread.sleep(1000);
			t2.start();
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
