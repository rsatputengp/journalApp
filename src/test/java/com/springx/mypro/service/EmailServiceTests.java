package com.springx.mypro.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @MockBean
    private JavaMailSender javaMailSender;


    @Test
    void testSendEmail() {
        emailService.sendEmail("rsatputengp@gmail.com",
                "Testing Java Email sender",
                  "Yoo Shinchan {^_^}");

        // ✅ Proper verification
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));

    }
}
