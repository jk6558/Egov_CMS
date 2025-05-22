package com.example.cmsapi.service;

import com.example.cmsapi.dto.GuideDto;
import com.example.cmsapi.entity.Guide;
import com.example.cmsapi.repository.GuideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class GuideServiceImpl implements GuideService {

    private final GuideRepository guideRepository;

    @Override
    public void saveGuide(GuideDto dto, MultipartFile image) {
        try {
            Guide.GuideBuilder builder = Guide.builder()
                    .categoryLarge(dto.getCategoryLarge())
                    .categoryMiddle(dto.getCategoryMiddle())
                    .categorySmall(dto.getCategorySmall())
                    .description(dto.getDescription());

            if (image != null && !image.isEmpty()) {
                builder.image(image.getBytes());
            }

            Guide guide = builder.build();
            guideRepository.save(guide);
        } catch (IOException e) {
            throw new RuntimeException("이미지 처리 중 오류 발생", e);
        }
    }


    public List<GuideDto> getAllGuides() {
        return guideRepository.findAll().stream().map(g -> {
            GuideDto dto = new GuideDto();
            dto.setId(g.getId());
            dto.setCategoryLarge(g.getCategoryLarge());
            dto.setCategoryMiddle(g.getCategoryMiddle());
            dto.setCategorySmall(g.getCategorySmall());
            dto.setDescription(g.getDescription());

            // 👇 이미지 바이트를 Base64 문자열로 변환해서 DTO에 저장
            if (g.getImage() != null) {
                String base64Image = Base64.getEncoder().encodeToString(g.getImage());
                dto.setImageData(base64Image);
            }

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<byte[]> getImageById(Long id) {
        return guideRepository.findById(id)
                .map(g -> ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"image\"")
                        .body(g.getImage()))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public void deleteGuide(Long id) {
        guideRepository.deleteById(id);
    }
}
