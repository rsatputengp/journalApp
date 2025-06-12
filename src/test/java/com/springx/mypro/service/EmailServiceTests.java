package com.springx.mypro.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @MockBean
    private JavaMailSender javaMailSender;


    @Test
    void testSendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("rsatputengp@gmail.com");
        message.setSubject("Test Subject");
        message.setText("Test Body");

        emailService.sendEmail(
                message.getTo()[0],
                message.getSubject(),
                message.getText()
        );

        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));

    }
}
