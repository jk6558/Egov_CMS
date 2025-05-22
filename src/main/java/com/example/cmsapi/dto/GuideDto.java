package com.example.cmsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuideDto {
    private Long id;

    @JsonProperty("categoryLarge")
    private String categoryLarge;

    @JsonProperty("categoryMiddle")
    private String categoryMiddle;

    @JsonProperty("categorySmall")
    private String categorySmall;

    @JsonProperty("description")
    private String description;

    @JsonProperty("imageData")
    private String imageData;
}

