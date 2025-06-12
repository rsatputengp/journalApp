package com.springx.mypro.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail() {
        emailService.sendEmail("sahayogsaurbh@gmail.com",
                "Testing Java Email sender",
                  "Yoo Shinchan {^_^}");
    }
}
