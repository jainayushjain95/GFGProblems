package multithreading;

public class TokenBucketFilter {

	public static void main(String[] args) throws InterruptedException {
		
	}

}


class Bucket {
	private int bucketCapacity;
	private long lastRequestTimestamp;
	private long availableTokensCount;
	
	public Bucket(int bucketCapacity) {
		this.bucketCapacity = bucketCapacity;
		lastRequestTimestamp = System.currentTimeMillis();
	}
	
	public synchronized void getToken() throws InterruptedException {
		availableTokensCount += (System.currentTimeMillis() - lastRequestTimestamp)/1000;
		if(availableTokensCount > bucketCapacity) {
			availableTokensCount = bucketCapacity;
		}
		
		if(availableTokensCount == 0) {
			Thread.sleep(1000);
		} else {
			availableTokensCount--;
		}
		lastRequestTimestamp = System.currentTimeMillis();
		System.out.println("\nGranting Token to " + Thread.currentThread().getName() + " at " + (System.currentTimeMillis()/1000));
	}
	
}