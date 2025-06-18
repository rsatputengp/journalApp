package com.springx.mypro.controller;

import com.springx.mypro.api.response.WeatherResponse;
import com.springx.mypro.entity.JournalEntry;
import com.springx.mypro.entity.User;
import com.springx.mypro.repository.UserRepository;
import com.springx.mypro.service.JournalEntryService;
import com.springx.mypro.service.UserService;
import com.springx.mypro.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

//    //http://localhost:8080/user/all
//    @GetMapping("/all")
//    public ResponseEntity<List<User>> getAllJournalEntries(){
//        List<User> allUsers = userService.getAllUsers();
//        if(!allUsers.isEmpty()){
//            return new ResponseEntity<>(allUsers,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    //http://localhost:8080/user/get/1
//    @GetMapping("/get/{id}")
//    public ResponseEntity<User> getUser(@PathVariable ObjectId id){
//        Optional<User> user = userService.getUser(id);
//        if(user.isPresent()){
//            return new ResponseEntity<>(user.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//

    //http://localhost:8080/user/update/1
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<User> updateUser(@RequestBody User user){

        try {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveUser(userInDb);
        return new ResponseEntity<>(user,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //    http://localhost:8080/user/delete/1
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteByUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Object> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse response = weatherService.getWeather("Mumbai");
        if(response != null){
        return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>("User Name : " + authentication.getName() + "\nWeather feels like ^-^ \n" + "No data found", HttpStatus.OK);
    }

}
