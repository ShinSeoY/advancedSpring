package com.sandy.advancedSpring.service;

import com.sandy.advancedSpring.domain.member.MyUser;
import com.sandy.advancedSpring.dto.AdminMemberRequestDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class UserServiceTests {

    private static final Logger log = LoggerFactory.getLogger(UserServiceTests.class);

    @Resource(name = "userService")
    private UserService userService;

    @Test
    void insertAdminWithMember() {
        // Given
        AdminMemberRequestDto adminMemberRequestDto = AdminMemberRequestDto.builder()
                .adminUsername("admin1")
                .adminPw("test1234")
                .phone("01011111111")
                .departmentId(1L)
                .userUsername("user1")
                .userPw("test1234")
                .build();

        // When & Then
        assertDoesNotThrow(() -> userService.insertAdminWithMember(adminMemberRequestDto));
    }

    @Test
    void index() {
        // Given
        String username = "sandy2";

        // When
        MyUser myUser = userService.index(username);

        // Then
        assertDoesNotThrow(() -> userService.index(username));
        assertNotNull(myUser);
    }

}
