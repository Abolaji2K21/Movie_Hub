package com.mavericktube.MaverickHub.Services;


import com.mavericktube.MaverickHub.dtos.requests.SendMailRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MailServiceTest {


    @Autowired
    private  MailService mailService;

    @Test
    void testSendEmail(){
        String Email = "abisoyeabolaji2k21@gmail.com";

        SendMailRequest mailRequest = new SendMailRequest();
        mailRequest.setContent("<p> Hello from the other side Of the hub</p>");
        mailRequest.setRecipientEmail(Email);
        mailRequest.setSubject("Welcome to the Hub");
        mailRequest.setRecipientName("John");
        String response = mailService.sendMail(mailRequest);

        assertThat(response).isNotNull();
        assertThat(response).containsIgnoringCase("success");
    }
}
