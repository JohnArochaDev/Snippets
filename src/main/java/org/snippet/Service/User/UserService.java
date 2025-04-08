package org.snippet.Service.User;

import org.snippet.Modal.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    // Save
    User saveUser(User user);

    // Update
    User updateUser(User user, UUID id);

    // Delete
    void deleteUser(UUID id);

    // Find by
    Optional<User> findById(UUID id);
    Optional<User> findByUsername(String username);

    // Login
    void login(String username, String password);
}
