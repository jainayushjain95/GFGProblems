package design.rate_limiter.dto;

import java.util.UUID;

public class User {
    private final String id;
    private final String name;

    public User(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}