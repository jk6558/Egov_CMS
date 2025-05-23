package com.example.cmsapi.controller;

import com.example.cmsapi.dto.GuideDto;
import com.example.cmsapi.service.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/components")
@RequiredArgsConstructor
public class GuideController {

    private final GuideService guideService;

    // 등록
    @PostMapping
    public ResponseEntity<String> uploadGuide(
            @ModelAttribute GuideDto guideDto,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        guideService.saveGuide(guideDto, image);
        return ResponseEntity.ok("등록 성공");
    }

    // 목록 조회
    @GetMapping
    public ResponseEntity<List<GuideDto>> getAllGuides() {
        return ResponseEntity.ok(guideService.getAllGuides());
    }

    // 이미지 조회
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        return guideService.getImageById(id);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuide(@PathVariable Long id) {
        guideService.deleteGuide(id);
        return ResponseEntity.noContent().build();
    }
}
