package com.example.demo.domain.port;

public interface ImageRepositoryPort {

    String searchImages(String query, int page, int size);

    String getImageInfo(String id);

    String getImageSizes(String id);
}
