package com.sandy.advancedSpring.service;

import com.sandy.advancedSpring.common.exception.ErrorCode;
import com.sandy.advancedSpring.common.exception.error.NotFoundException;
import com.sandy.advancedSpring.common.exception.error.UserException;
import com.sandy.advancedSpring.domain.admin.Department;
import com.sandy.advancedSpring.domain.admin.MyAdmin;
import com.sandy.advancedSpring.domain.member.MyUser;
import com.sandy.advancedSpring.dto.AdminMemberRequestDto;
import com.sandy.advancedSpring.repository.admin.DepartmentRepository;
import com.sandy.advancedSpring.repository.admin.MyAdminRepository;
import com.sandy.advancedSpring.repository.member.MyUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final MyUserRepository myUserRepository;
    private final MyAdminRepository myAdminRepository;
    private final DepartmentRepository departmentRepository;

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

    @Transactional
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
        departmentRepository.save(
                Department.builder()
                        .name(adminMemberRequestDto.getDepartmentName())
                        .build()
        );
        if (true) {
            throw new UserException(ErrorCode.INVALID);
        }
    }


}
