package org.snippet.Service;

import org.snippet.Modal.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    // Save
    User saveUser(User user);

    // Update
    User updateUser(User user, UUID id);

    // Delete
    void deleteUser(User user, UUID id);
}
