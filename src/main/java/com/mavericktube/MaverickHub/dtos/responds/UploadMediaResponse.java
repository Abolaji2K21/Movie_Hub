package com.mavericktube.MaverickHub.dtos.responds;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mavericktube.MaverickHub.Models.Category;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UploadMediaResponse {
    @JsonProperty("media_Url")
    private String Url;
    @JsonProperty("media_description")
    private String description;
    private Long id;
    private Category category;

}
