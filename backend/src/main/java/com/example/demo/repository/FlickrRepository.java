package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class FlickrRepository {

    @Value("${flickr.api.key}")
    private String apiKey;

    @Value("${flickr.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public FlickrRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchImages(String text, int page, int size) {

        String url = apiUrl +
                "?method=flickr.photos.search" +
                "&api_key=" + apiKey +
                "&text=" + text +
                "&page=" + page +
                "&per_page=" + size +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=description,owner_name,tags,url_m,url_l";

        return restTemplate.getForObject(url, String.class);
    }

    public String getImageInfo(String photoId) {

        String url = apiUrl +
                "?method=flickr.photos.getInfo" +
                "&api_key=" + apiKey +
                "&photo_id=" + photoId +
                "&format=json" +
                "&nojsoncallback=1";

        return restTemplate.getForObject(url, String.class);
    }
}
