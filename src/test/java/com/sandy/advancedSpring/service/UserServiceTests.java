package com.sandy.advancedSpring.service;

import com.sandy.advancedSpring.domain.member.MyUser;
import com.sandy.advancedSpring.dto.AdminMemberRequestDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserServiceTests {

    private static final Logger log = LoggerFactory.getLogger(UserServiceTests.class);

    @Resource(name = "userService")
    private UserService userService;

    @Test
    void contextLoads() {
//        index("sandy1");
        insertAdminWithMember(
                AdminMemberRequestDto.builder()
                        .adminUsername("admin1")
                        .adminPw("test1234")
                        .phone("01011111111")
                        .departmentId(1L)
                        .userUsername("user1")
                        .userPw("test1234")
                        .build()
        );
    }

    private void index(String username) {
        try {
            MyUser myUser = userService.index(username);
            log.info("** myUser : {}", myUser);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    private void insertAdminWithMember(AdminMemberRequestDto adminMemberRequestDto) {
        try {
            userService.insertAdminWithMember(adminMemberRequestDto);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

}
