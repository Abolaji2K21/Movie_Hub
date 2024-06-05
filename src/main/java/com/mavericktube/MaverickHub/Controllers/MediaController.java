package com.mavericktube.MaverickHub.Controllers;


import com.mavericktube.MaverickHub.Services.MediaService;
import com.mavericktube.MaverickHub.dtos.requests.UploadMediaRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/media")
public class MediaController {

    private final MediaService mediaService;
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadMedia(@ModelAttribute UploadMediaRequest uploadMediaRequest) throws IOException {
        return ResponseEntity.status(CREATED).body(mediaService.uploadVed(uploadMediaRequest));
}

    @GetMapping
    public  ResponseEntity<?> getMediaForUser(@RequestParam Long userId){
        return ResponseEntity.ok(mediaService.getMediaFor(userId));

    }
}