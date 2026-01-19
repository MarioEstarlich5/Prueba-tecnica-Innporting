package com.example.demo.infrastructure.web;

import com.example.demo.dto.ImageDetailDto;
import com.example.demo.dto.SearchResponseDto;
import com.example.demo.application.service.ImageService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "*")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/search")
    public SearchResponseDto searchImages(
            @RequestParam String query,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return imageService.searchImages(query, page, size);
    }

    @GetMapping("/{id}")
    public ImageDetailDto getImageDetail(@PathVariable String id) {
        return imageService.getImageDetail(id);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String id) {
        ImageDetailDto detail = imageService.getImageDetail(id);

        try {
            URL url = new URL(detail.getDownloadUrl());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try (InputStream is = url.openStream()) {
                is.transferTo(baos);
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + detail.getTitle() + ".jpg\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(baos.toByteArray());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
