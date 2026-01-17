package com.example.demo.controller;

import com.example.demo.dto.ImageDetailDto;
import com.example.demo.dto.SearchResponseDto;
import com.example.demo.service.ImageService;
import org.springframework.web.bind.annotation.*;

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
}
