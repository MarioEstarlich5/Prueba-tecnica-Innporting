package com.example.demo.service;

import com.example.demo.dto.ImageDetailDto;
import com.example.demo.dto.ImageDto;
import com.example.demo.repository.FlickrRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import com.example.demo.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    private final FlickrRepository flickrRepository;
    private final ObjectMapper objectMapper;

    public ImageService(FlickrRepository flickrRepository) {
        this.flickrRepository = flickrRepository;
        this.objectMapper = new ObjectMapper();
    }


    public List<ImageDto> searchImages(String query, int page, int size) {

        try {
            String response = flickrRepository.searchImages(query, page, size);
            JsonNode root = objectMapper.readTree(response);

            List<ImageDto> images = new ArrayList<>();

            JsonNode photos = root.path("photos").path("photo");

            for (JsonNode photo : photos) {
                ImageDto dto = new ImageDto();
                dto.setId(photo.path("id").asText());
                dto.setTitle(photo.path("title").asText());
                dto.setImageUrl(photo.path("url_m").asText());

                images.add(dto);
            }

            return images;

        } catch (Exception e) {
            throw new ApiException("Error al buscar imágenes en Flickr");
        }
    }


    public ImageDetailDto getImageDetail(String imageId) {

        try {
            String response = flickrRepository.getImageInfo(imageId);
            JsonNode root = objectMapper.readTree(response);
            JsonNode photo = root.path("photo");

            ImageDetailDto dto = new ImageDetailDto();
            dto.setId(imageId);
            dto.setTitle(photo.path("title").path("_content").asText());
            dto.setAuthor(photo.path("owner").path("username").asText());
            dto.setDescription(photo.path("description").path("_content").asText());

            StringBuilder tagsBuilder = new StringBuilder();
            for (JsonNode tag : photo.path("tags").path("tag")) {
                tagsBuilder.append(tag.path("_content").asText()).append(" ");
            }
            dto.setTags(tagsBuilder.toString().trim());

            dto.setImageUrl(
                    photo.path("urls").path("url").get(0).path("_content").asText()
            );

            return dto;

        } catch (Exception e) {
            throw new ApiException("Error al buscar imágenes en Flickr");
        }
    }
}
