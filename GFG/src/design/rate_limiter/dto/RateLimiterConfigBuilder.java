package design.rate_limiter.dto;

import design.rate_limiter.constants.RateLimiterType;

public class RateLimiterConfigBuilder {
    private RateLimiterType type;
    private int capacity;
    private long windowDurationInMills;
    private int refillRate;

    public RateLimiterConfigBuilder type(RateLimiterType type) {
        this.type = type;
        return this;
    }

    public RateLimiterConfigBuilder capacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public RateLimiterConfigBuilder windowDurationInMills(long windowDurationInMills) {
        this.windowDurationInMills = windowDurationInMills;
        return this;
    }

    public RateLimiterConfigBuilder refillRate(int refillRate) {
        this.refillRate = refillRate;
        return this;
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

    public RateLimiterConfig build() {
        return new RateLimiterConfig(this);
    }
}
