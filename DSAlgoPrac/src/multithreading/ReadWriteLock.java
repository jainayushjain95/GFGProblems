package multithreading;

public class ReadWriteLock {

	public static void main(String[] args) {
		int x  = 1;
		for(x = 2; x < 4; x++);
		System.out.println(x);
	}

}


class ReadWriteLockSolve {

	boolean isWriteLockAcquired;
	int readsAcquired;
	
	public ReadWriteLockSolve(boolean isWriteLockAqcquired, int readsAcquired) {
		this.isWriteLockAcquired = isWriteLockAqcquired;
		this.readsAcquired = readsAcquired;
	}
	
	public synchronized void acquireReadLock() throws InterruptedException {
		while(isWriteLockAcquired) {
			wait();
		}
		readsAcquired++;
	}

	public synchronized void releaseReadLock() {
		readsAcquired--;
		notify();
	}

	public synchronized void acquireWriteLock() throws InterruptedException {
		while(isWriteLockAcquired || readsAcquired != 0) {
			wait();
		}
		isWriteLockAcquired = true;
	}

	public synchronized void releaseWriteLock() {
		isWriteLockAcquired = false;
		notify();
	}
	
}