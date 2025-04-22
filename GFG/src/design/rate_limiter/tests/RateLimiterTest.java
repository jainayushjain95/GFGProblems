package design.rate_limiter.tests;


import design.rate_limiter.services.TokenBucketRateLimiter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RateLimiterTest {

    private TokenBucketRateLimiter rateLimiter;

    @Before
    public void setUp() {
        rateLimiter = new TokenBucketRateLimiter(5, 2);
    }

    @Test
    public void testInitialBurstAllowed() {
        for (int i = 0; i < 5; i++) {
            assertTrue(rateLimiter.isAllowed());
        }
    }

    @Test
    public void testExceedingBurstLimit() {
        for (int i = 0; i < 5; i++) {
            rateLimiter.isAllowed();
        }
        assertFalse(rateLimiter.isAllowed());
    }

    @Test
    public void testRefillAfterWait() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            rateLimiter.isAllowed();
        }
        assertFalse(rateLimiter.isAllowed());

        Thread.sleep(1100);
        assertTrue(rateLimiter.isAllowed());
    }
}
