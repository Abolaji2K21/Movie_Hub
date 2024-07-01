package com.mavericktube.MaverickHub.Services;


import com.mavericktube.MaverickHub.config.MailConfig;
import com.mavericktube.MaverickHub.dtos.requests.BrevoMailRequest;
import com.mavericktube.MaverickHub.dtos.requests.Recipient;
import com.mavericktube.MaverickHub.dtos.requests.SendMailRequest;
import com.mavericktube.MaverickHub.dtos.requests.Sender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class MailServiceImpl implements  MailService{


    private final MailConfig mailConfig;
    @Override
    public String sendMail(SendMailRequest mailRequest) {

        RestTemplate restTemplate = new RestTemplate();
        String url = mailConfig.getMailApiUrl();
        BrevoMailRequest request = new BrevoMailRequest();
        request.setContent(mailRequest.getContent());
        request.setSender(new Sender());
        request.setRecipient(List.of(new Recipient(mailRequest.getRecipientEmail(), mailRequest.getRecipientName())));
        request.setSubject(mailRequest.getSubject());
        restTemplate.postForEntity(url,request,)


    }
}
