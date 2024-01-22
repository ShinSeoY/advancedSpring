package com.sandy.advancedSpring.controller;

import com.sandy.advancedSpring.dto.AdminMemberRequestDto;
import com.sandy.advancedSpring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainController {

    private final UserService userService;

    @GetMapping("/index/{username}")
    public ResponseEntity index(@PathVariable String username) {
        return new ResponseEntity<>(userService.index(username), HttpStatus.OK);
    }

    @GetMapping("/create")
    public ResponseEntity create() {
        userService.insertAdminWithMember(
                AdminMemberRequestDto.builder()
                        .adminUsername("admin1")
                        .adminPw("test1234")
                        .phone("01011111111")
                        .departmentId(1L)
                        .userUsername("user1")
                        .userPw("test1234")
                        .build()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
