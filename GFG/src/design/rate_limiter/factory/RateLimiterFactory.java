package design.rate_limiter.factory;

import design.rate_limiter.services.FixedWindowCounterRateLimiter;
import design.rate_limiter.services.RateLimiter;
import design.rate_limiter.services.TokenBucketRateLimiter;
import design.rate_limiter.dto.RateLimiterConfig;

public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(RateLimiterConfig rateLimiterConfig) {
        switch (rateLimiterConfig.getType()) {
            case TOKEN_BUCKET:
                return new TokenBucketRateLimiter(rateLimiterConfig.getCapacity(), rateLimiterConfig.getRefillRate());
            case FIXED_WINDOW:
                return new FixedWindowCounterRateLimiter(rateLimiterConfig.getCapacity(), rateLimiterConfig.getWindowDurationInMills());
            default:
                throw new IllegalArgumentException("Unsupported rate_limiter");
        }
    }
}
