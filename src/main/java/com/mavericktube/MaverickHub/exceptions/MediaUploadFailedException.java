package com.mavericktube.MaverickHub.exceptions;

public class MediaUploadFailedException extends RuntimeException {
    public MediaUploadFailedException(String message) {
        super(message);
    }
}