package com.mavericktube.MaverickHub.Services;


import com.mavericktube.MaverickHub.config.MailConfig;
import com.mavericktube.MaverickHub.dtos.requests.BrevoMailRequest;
import com.mavericktube.MaverickHub.dtos.requests.Recipient;
import com.mavericktube.MaverickHub.dtos.requests.SendMailRequest;
import com.mavericktube.MaverickHub.dtos.requests.Sender;
import com.mavericktube.MaverickHub.dtos.responds.BrevoMailResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.set("api-key", mailConfig.getMailApiKey());
        headers.set("accept",APPLICATION_JSON.toString());
        RequestEntity<?> httpRequest = new RequestEntity<>(request,headers,HttpMethod.POST, URI.create(url));
        ResponseEntity<BrevoMailResponse> response =restTemplate.postForEntity(url,httpRequest, BrevoMailResponse.class);

        if (response.getBody()!= null && response.getStatusCode() == HttpStatusCode.valueOf(201)) {
            return "mail sent successfully";
        }
        else throw new RuntimeException("Email sending Failed");
    }
}
