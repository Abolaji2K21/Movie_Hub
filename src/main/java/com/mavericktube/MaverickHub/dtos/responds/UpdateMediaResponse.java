package com.mavericktube.MaverickHub.dtos.responds;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mavericktube.MaverickHub.Models.Category;
import lombok.Data;


@Data
public class UpdateMediaResponse {
    @JsonProperty("media_Url")
    private String Url;
    @JsonProperty("media_description")
    private String descriptions;
    private Long id;
    private Category category;
}
