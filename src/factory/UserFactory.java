package entity;

import java.time.LocalDateTime;

public class UserFactory {
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     * @return
     */

    public User create(String name, String password) {
        return new User(name, password);
    }
}