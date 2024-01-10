package com.sandy.advancedSpring.service;

import com.sandy.advancedSpring.common.exception.error.NotFoundException;
import com.sandy.advancedSpring.domain.admin.MyAdmin;
import com.sandy.advancedSpring.domain.member.MyUser;
import com.sandy.advancedSpring.dto.AdminMemberRequestDto;
import com.sandy.advancedSpring.repository.admin.MyAdminRepository;
import com.sandy.advancedSpring.repository.member.MyUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final MyUserRepository myUserRepository;
    private final MyAdminRepository myAdminRepository;

    public MyUser index(String username) {
        MyUser myUser = myUserRepository.findByUsername(username);

        if (myUser == null) {
            throw new NotFoundException();
        } else {
            return myUser;
        }
    }

    public void create() {
        myUserRepository.save(MyUser.builder()
                .username("sandy1")
                .password("test1234")
                .build());
    }

    public void insertAdminWithMember(AdminMemberRequestDto adminMemberRequestDto) {
        myAdminRepository.save(
                MyAdmin.builder()
                        .username(adminMemberRequestDto.getAdminUsername())
                        .password(adminMemberRequestDto.getAdminPw())
                        .phone(adminMemberRequestDto.getPhone())
                        .departmentId(adminMemberRequestDto.getDepartmentId())
                        .build()
        );

        myUserRepository.save(
                MyUser.builder()
                        .username(adminMemberRequestDto.getUserUsername())
                        .password(adminMemberRequestDto.getUserPw())
                        .build()
        );
    }


}
