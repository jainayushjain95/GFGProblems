package design.rate_limiter.services;

public class TokenBucketRateLimiter implements RateLimiter {

    private final int capacity;
    private final int refillRate;

    private long lastRefilledTime;
    private double currentNoOfTokens;

    private final Object lock;

    public TokenBucketRateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.lastRefilledTime = System.currentTimeMillis();
        this.currentNoOfTokens = capacity;
        this.lock = new Object();
    }

    private void refill() {
        long elapsedTime  = System.currentTimeMillis() - lastRefilledTime;
        if(elapsedTime > 0) {
            lastRefilledTime = System.currentTimeMillis();
            currentNoOfTokens = Math.min(capacity, currentNoOfTokens + (refillRate * elapsedTime)/1000.0);
        }
    }

    public boolean isAllowed() {
        synchronized (lock) {
            refill();
            if(currentNoOfTokens >= 1) {
                currentNoOfTokens--;
                return true;
            }
            return false;
        }
    }

}
