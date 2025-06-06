package com.springx.mypro.service;

import com.springx.mypro.entity.JournalEntry;
import com.springx.mypro.entity.User;
import com.springx.mypro.repository.JournalEntryRepository;
import com.springx.mypro.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(User user){
            userRepository.save(user);
    }

    public void saveUser(User user) throws Exception {
        try {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        } catch (Exception e) {
            log.error("Error saving user: {} - ",user.getUserName(), e);
            throw new Exception("Error saving user: " + e.getMessage());
        }
    }

    public boolean saveUserTest(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
//            {} -> this bracket are replace with "user.getUserName()"
//2025-06-03 16:19:47.502 ERROR 18420 --- [nio-8080-exec-1] com.springx.mypro.service.UserService    : Error saving user: Ritik -
                    log.error("Error saving user: {} - ",user.getUserName(), e);
            return false;
        }
    }

    public void saveAdmin(User user) throws Exception {
        try {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
        } catch (Exception e) {
            log.error("Error saving user: {} - ",user.getUserName(), e);
            throw new Exception("Error saving user: " + e.getMessage());
        }
    }

    public List<User> getAllUsers (){
        return userRepository.findAll();
    }

    public Optional<User> getUser(ObjectId id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user;
        }
        log.error("User not found: {}", id);
        return Optional.empty();
    }

    public void deleteUser(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName (String userName){

        User user = userRepository.findByUserName(userName);
        if(user != null){
            return user;
        }
        log.error("User not found: {}", userName);
        return null;

    }

}
