package com.mavericktube.MaverickHub.Services;


import com.mavericktube.MaverickHub.dtos.requests.SendMailRequest;

public interface MailService {

    String sendMail(SendMailRequest mailRequest);
}
