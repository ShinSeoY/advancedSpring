package com.sandy.advancedSpring.service;

import com.sandy.advancedSpring.domain.MyUser;
import com.sandy.advancedSpring.exception.error.NotFoundException;
import com.sandy.advancedSpring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public MyUser index(String username) {
        MyUser myUser = userRepository.findByUsername(username);

        if (myUser == null) {
            throw new NotFoundException();
        } else {
            return myUser;
        }
    }


}
