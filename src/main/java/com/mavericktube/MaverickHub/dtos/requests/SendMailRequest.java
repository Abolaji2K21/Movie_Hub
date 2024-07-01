package com.mavericktube.MaverickHub.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class SendMailRequest {

    private String recipientEmail;
    private String subject;
    private String recipientName;
    private String content;

}
