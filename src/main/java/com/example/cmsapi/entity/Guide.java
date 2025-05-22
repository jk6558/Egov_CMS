package com.example.cmsapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "guides")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder   // << 이게 반드시 있어야 builder() 사용 가능
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryLarge;
    private String categoryMiddle;
    private String categorySmall;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @Lob
    private byte[] image;
}
