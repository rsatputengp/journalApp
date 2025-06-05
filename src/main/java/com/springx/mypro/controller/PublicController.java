package com.springx.mypro.controller;

import com.springx.mypro.entity.User;
import com.springx.mypro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

    //http://localhost:8080/user/save
    @PostMapping("/save")
    public ResponseEntity<?> createUser(@RequestBody User myUser){
        try {
            userService.saveUser(myUser);
            return new ResponseEntity<>(myUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>( "Dublicate User Entry " ,HttpStatus.BAD_REQUEST);
        }
    }
}
