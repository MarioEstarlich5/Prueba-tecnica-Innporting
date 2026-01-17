package com.example.demo.dto;

import java.util.List;

public class SearchResponseDto {

    private int page;
    private int totalPages;
    private List<ImageDto> images;

    public SearchResponseDto(int page, int totalPages, List<ImageDto> images) {
        this.page = page;
        this.totalPages = totalPages;
        this.images = images;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<ImageDto> getImages() {
        return images;
    }
}
