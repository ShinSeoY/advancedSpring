package com.sandy.advancedSpring.repository.member;

import com.sandy.advancedSpring.domain.member.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    MyUser findByUsername(String username);
}
