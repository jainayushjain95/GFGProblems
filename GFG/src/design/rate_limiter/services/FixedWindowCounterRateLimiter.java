package design.rate_limiter.services;

import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowCounterRateLimiter implements RateLimiter {
    private final int  capacity;
    private final long windowDurationInMills;

    private AtomicInteger requestCountInThisWindow;
    private long windowStartTimeInMills;

    public FixedWindowCounterRateLimiter(int capacity, long windowDurationInMills) {
        this.capacity = capacity;
        this.windowDurationInMills = windowDurationInMills;
        this.windowStartTimeInMills = System.currentTimeMillis();
        this.requestCountInThisWindow = new AtomicInteger(0);
    }

    @Override
    public boolean isAllowed() {
        long timeElapsed = System.currentTimeMillis() - windowStartTimeInMills;
        if(timeElapsed > windowDurationInMills) {
            requestCountInThisWindow.set(0);
            windowStartTimeInMills = System.currentTimeMillis();
        }
        if(requestCountInThisWindow.get() < capacity) {
            requestCountInThisWindow.incrementAndGet();
            return true;
        }
        return false;
    }
}
