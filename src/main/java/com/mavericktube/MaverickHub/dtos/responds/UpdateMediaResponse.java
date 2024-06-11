package com.mavericktube.MaverickHub.dtos.responds;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mavericktube.MaverickHub.Models.Category;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UpdateMediaResponse {
    @JsonProperty("media_Url")
    private String url;
    @JsonProperty("media_description")
    private String description;
    private Long id;
    private Category category;
    @JsonProperty("created_at")
    private LocalDateTime timeCreated;
    @JsonProperty("updated_at")
    private LocalDateTime timeUpdated;
}
