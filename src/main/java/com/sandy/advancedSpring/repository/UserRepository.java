package com.sandy.advancedSpring.repository;

import com.sandy.advancedSpring.domain.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    MyUser findByUsername(String username);
}
