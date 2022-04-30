package com.example.market.service.impl;

import com.example.market.model.entity.User;
import com.example.market.repository.UserRepository;
import com.example.market.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void initUsers() {

        if (userRepository.count() == 0) {

            User user1 = new User();
            user1.setUsername("User1");
            user1.setAccount(0.0);

            User user2 = new User();
            user2.setUsername("User2");
            user2.setAccount(0.0);

            userRepository.save(user1);
            userRepository.save(user2);
        }
    }
}
