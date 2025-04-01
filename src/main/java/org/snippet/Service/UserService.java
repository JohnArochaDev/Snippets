package org.snippet.Service;

import org.snippet.Modal.User;

import java.util.Optional;

public interface UserService {
    // Save
    User saveUser(User user);

    // Update
    User updateUser(User user);

    // Delete
    void deleteUser(User user);

    // Find by
    Optional<User> findById(Integer id);
}
