package design.rate_limiter.dto;

import design.rate_limiter.constants.RateLimiterType;

public class RateLimiterConfig {
    private final RateLimiterType type;
    private final int capacity;
    private final long windowDurationInMills;
    private final int refillRate;

    public RateLimiterConfig(RateLimiterConfigBuilder rateLimiterConfigBuilder) {
        this.capacity = rateLimiterConfigBuilder.getCapacity();
        this.windowDurationInMills = rateLimiterConfigBuilder.getWindowDurationInMills();
        this.refillRate = rateLimiterConfigBuilder.getRefillRate();
        this.type = rateLimiterConfigBuilder.getType();
    }

    public RateLimiterType getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public long getWindowDurationInMills() {
        return windowDurationInMills;
    }

    public int getRefillRate() {
        return refillRate;
    }
}
