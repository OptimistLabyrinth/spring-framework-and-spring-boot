package com.demo.kiseong.yoo.demo.domain.user.core;

import com.demo.kiseong.yoo.demo.domain.user.entity.User;
import com.demo.kiseong.yoo.demo.domain.user.infra.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("user not found with the id " + userId));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User update(User user) {
        User userToSave = findById(user.getId());
        userToSave.setFirstName(user.getFirstName());
        userToSave.setLastName(user.getLastName());
        userToSave.setEmail(user.getEmail());
        return userRepository.save(userToSave);
    }

    public User delete(Long userId) {
        User userToDelete = findById(userId);
        userRepository.delete(userToDelete);
        return userToDelete;
    }

}
