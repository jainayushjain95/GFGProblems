package design.rate_limiter;

import design.rate_limiter.constants.RateLimiterType;
import design.rate_limiter.dto.RateLimiterConfig;
import design.rate_limiter.dto.RateLimiterConfigBuilder;
import design.rate_limiter.dto.User;
import design.rate_limiter.factory.RateLimiterFactory;
import design.rate_limiter.services.RateLimiter;
import design.rate_limiter.services.RateLimiterManager;

public class Main {
    public static void main(String[] args) {

        User user1 = new User("Ayush");
        User user2 = new User("Jain");

        RateLimiterConfig tokenBucketConfig = new RateLimiterConfigBuilder()
                .type(RateLimiterType.TOKEN_BUCKET)
                .capacity(5)
                .refillRate(2)
                .build();

        RateLimiterConfig fixedWindowConfig = new RateLimiterConfigBuilder()
                .type(RateLimiterType.FIXED_WINDOW)
                .capacity(5)
                .windowDurationInMills(60000)
                .build();

        RateLimiter rateLimiterTokenBucket = RateLimiterFactory.createRateLimiter(tokenBucketConfig);
        RateLimiter rateLimiterFixedWindow = RateLimiterFactory.createRateLimiter(fixedWindowConfig);

        RateLimiterManager rateLimiterManager = new RateLimiterManager();
        rateLimiterManager.registerUser(user1, tokenBucketConfig);
        rateLimiterManager.registerUser(user2, fixedWindowConfig);

        for (int i = 0; i < 10; i++) {
            System.out.println("Request " + (i + 1) + ": " + (rateLimiterTokenBucket.isAllowed() ? "Allowed" : "Denied"));
            try {
                Thread.sleep(100);  // Simulate delay between requests
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("Request " + (i + 1) + ": " + (rateLimiterFixedWindow.isAllowed() ? "Allowed" : "Denied"));
            try {
                Thread.sleep(300);  // Simulate delay between requests
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
