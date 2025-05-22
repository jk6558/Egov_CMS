package com.example.cmsapi.service;

import com.example.cmsapi.dto.GuideDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GuideService {
    void saveGuide(GuideDto dto, MultipartFile image);
    List<GuideDto> getAllGuides();
    ResponseEntity<byte[]> getImageById(Long id);
    void deleteGuide(Long id);
}
