package multithreading;

public class Main2 {

	public static void main(String[] args) {
		
		CountDown countDown = new CountDown();
		CountDownThread countDownThread1 = new CountDownThread(countDown);
		countDownThread1.setName("A");
		
		CountDownThread countDownThread2 = new CountDownThread(countDown);
		countDownThread2.setName("B");
		
		countDownThread1.start();
		countDownThread2.start();
		
	}
}


class CountDown {
	
	private int i;
	
	public void doCountDown() {
		synchronized (this) {
			for(i = 3; i >= 0; i--) {
				System.out.println(Thread.currentThread().getName() + ", i = " + i);
			}	
		}
	}
}

class CountDownThread extends Thread {
	
	private CountDown threadCountDown;

	public CountDownThread(CountDown threadCountDown) {
		this.threadCountDown = threadCountDown;
	}
	
	public void run() {
		threadCountDown.doCountDown();
	}
	
	
}
