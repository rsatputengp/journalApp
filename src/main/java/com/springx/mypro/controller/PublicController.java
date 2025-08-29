package com.springx.mypro.controller;

import com.springx.mypro.entity.User;
import com.springx.mypro.service.UserDetailsServiceImpl;
import com.springx.mypro.service.UserService;
import com.springx.mypro.utilis.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

    //http://localhost:8080/user/save
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User myUser){
        try {
            userService.saveUser(myUser);
            return new ResponseEntity<>(myUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>( "Dublicate User Entry " ,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User myUser){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(myUser.getUserName(), myUser.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(myUser.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurred while createAuthenticationToken ",e);
            return new ResponseEntity<>( "Incorrect username or password" ,HttpStatus.BAD_REQUEST);
        }
    }
}
