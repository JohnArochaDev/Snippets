package org.snippet.Service;

import org.snippet.Modal.User;
import org.snippet.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            return userRepository.save(userOptional.get());
        } else {
            throw new RuntimeException("Snippet not found");
        }
    }

    @Override
    public void deleteUser(User user, UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(userRepository::delete);
    }
}
