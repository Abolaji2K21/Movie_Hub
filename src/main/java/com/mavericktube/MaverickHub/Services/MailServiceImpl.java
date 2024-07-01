package com.mavericktube.MaverickHub.Services;


import com.mavericktube.MaverickHub.config.MailConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl implements  MailService{


    private final MailConfig mailConfig;
    @Override
    public String sendMail(String email) {
        return null;
    }
}
