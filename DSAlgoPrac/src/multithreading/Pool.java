package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Pool {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		System.out.println("A");
		List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
		
		for(int i = 0;i < 1000; i++) {
			futures.add(executorService.submit(new Task()));
		}executorService.sub
		
		System.out.println("B");
		Math.max(3, 3);
		for(Future future : futures) {
			System.out.println(future.get());
		}
	}
}

class Task implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		Thread.sleep(1000);
		return (int)(1 + Math.random() * 100);
	}
	
}
