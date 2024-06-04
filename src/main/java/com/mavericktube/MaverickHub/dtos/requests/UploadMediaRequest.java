package com.mavericktube.MaverickHub.dtos.requests;

import com.mavericktube.MaverickHub.Models.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadMediaRequest {

    private MultipartFile mediaFile;
    private String description;
    private Category category;
    private Long userId;

}
