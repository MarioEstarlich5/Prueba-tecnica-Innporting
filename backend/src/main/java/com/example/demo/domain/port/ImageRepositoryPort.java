package com.example.demo.domain.port;

import com.example.demo.dto.ImageDetailDto;
import com.example.demo.dto.SearchResponseDto;

public interface ImageRepositoryPort {
    SearchResponseDto searchImages(String query, int page, int size);
    ImageDetailDto getImageDetail(String id);
}
