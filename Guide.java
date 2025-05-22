package com.example.cmsapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryLarge;
    private String categoryMiddle;
    private String categorySmall;

    @Lob
    private String description;

    @Lob
    private byte[] image;

    private String imageType; // 예: image/png
}
