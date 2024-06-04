package com.mavericktube.MaverickHub.dtos.requests;

import com.mavericktube.MaverickHub.Models.Category;
import lombok.Data;

@Data
public class UpdateMediaRequest {
    private Long id;
    private String description;
    private Category category;}
