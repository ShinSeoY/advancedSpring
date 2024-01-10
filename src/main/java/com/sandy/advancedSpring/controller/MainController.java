package com.sandy.advancedSpring.controller;

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
        userService.create();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
