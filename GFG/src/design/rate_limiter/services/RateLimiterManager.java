package design.rate_limiter.services;

import design.rate_limiter.dto.RateLimiterConfig;
import design.rate_limiter.dto.User;
import design.rate_limiter.factory.RateLimiterFactory;

import java.util.*;

public class RateLimiterManager {
    private Map<String, RateLimiter> rateLimitersMap;

    public RateLimiterManager() {
        this.rateLimitersMap = new HashMap<>();
    }

    public void registerUser(User user, RateLimiterConfig rateLimiterConfig) {
        rateLimitersMap.put(user.getId(), RateLimiterFactory.createRateLimiter(rateLimiterConfig));
    }

    public RateLimiter getRateLimiter(String userId) {
        return rateLimitersMap.get(userId);
    }
}
