package com.springx.mypro.service;

import com.springx.mypro.entity.User;
import com.springx.mypro.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;

//    @ParameterizedTest
//    @ValueSource(strings = {"RamJi", "Ram", "RamJi123"})
//    public void testFindByUserName_ValidUserName(String userName) {
//        User user = userRepository.findByUserName(userName);
//        assertNotNull(user);
//    }

//    @ParameterizedTest
//    @CsvSource({"RamJi", "Ram", "RamJi123"})
//    public void testFindByUserName_ValidUserName_CsvSource(String userName) {
//        User user = userRepository.findByUserName(userName);
//        assertNotNull(user);
//    }

//    @Test
//    public void testFindByUserNameCust() {
//        User user = userRepository.findByUserName("RamJi");
//        assertTrue(!user.getJournalEntries().isEmpty());
//        assertNotNull(user.getJournalEntries().get(0));
//    }

//    @ParameterizedTest
//    @ArgumentsSource(UserArgunmentProvider.class)
//    public void testSaveNewUser(User user) {
//        assertTrue(userService.saveUserTest(user));
//    }
}
