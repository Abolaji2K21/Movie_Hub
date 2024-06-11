package com.mavericktube.MaverickHub.Services;


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


        String response = mailService.sendMail(Email);

        assertThat(response).isNotNull();
        assertThat(response).containsIgnoringCase("success");
    }
}
