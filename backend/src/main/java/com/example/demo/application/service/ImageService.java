package com.example.demo.application.service;

import com.example.demo.dto.ImageDetailDto;
import com.example.demo.dto.ImageDto;
import com.example.demo.dto.SearchResponseDto;
import com.example.demo.exception.ApiException;
import com.example.demo.repository.FlickrRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

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

    public SearchResponseDto searchImages(String query, int page, int size) {
        try {
            String response = flickrRepository.searchImages(query, page, size);
            JsonNode root = objectMapper.readTree(response);
            JsonNode photosNode = root.path("photos");

            int currentPage = photosNode.path("page").asInt();
            int totalPages = photosNode.path("pages").asInt();

            List<ImageDto> images = new ArrayList<>();

            for (JsonNode photo : photosNode.path("photo")) {
                ImageDto dto = new ImageDto();
                dto.setId(photo.path("id").asText());
                dto.setTitle(photo.path("title").asText());
                dto.setAuthor(photo.path("ownername").asText());
                dto.setThumbnailUrl(photo.path("url_m").asText());
                dto.setImageUrl(photo.path("url_m").asText());
                images.add(dto);
            }

            return new SearchResponseDto(currentPage, totalPages, images);

        } catch (Exception e) {
            throw new ApiException("Error al buscar imágenes en Flickr");
        }
    }

    public ImageDetailDto getImageDetail(String imageId) {
        try {
            String infoResponse = flickrRepository.getImageInfo(imageId);
            JsonNode infoRoot = objectMapper.readTree(infoResponse);
            JsonNode photo = infoRoot.path("photo");

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

            String sizesResponse = flickrRepository.getImageSizes(imageId);
            JsonNode sizesRoot = objectMapper.readTree(sizesResponse);
            JsonNode sizes = sizesRoot.path("sizes").path("size");

            String imageUrl = "";
            String downloadUrl = "";
            int width = 0;
            int height = 0;

            for (JsonNode size : sizes) {
                String label = size.path("label").asText();

                if (label.equals("Large")) {
                    imageUrl = size.path("source").asText();
                }

                if (label.equals("Original")) {
                    downloadUrl = size.path("source").asText();
                    width = size.path("width").asInt();
                    height = size.path("height").asInt();
                }
            }

            if (imageUrl.isEmpty() && sizes.size() > 0) {
                imageUrl = sizes.get(0).path("source").asText();
            }

            if (downloadUrl.isEmpty()) {
                downloadUrl = imageUrl;
            }

            dto.setImageUrl(imageUrl);
            dto.setDownloadUrl(downloadUrl);
            dto.setWidth(width);
            dto.setHeight(height);

            return dto;

        } catch (Exception e) {
            throw new ApiException("Error al obtener detalle de imagen");
        }
    }
}
