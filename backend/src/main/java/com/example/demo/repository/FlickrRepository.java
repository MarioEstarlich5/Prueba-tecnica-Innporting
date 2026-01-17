package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class FlickrRepository {

    private static final String API_KEY = "aa80857dbe0f4136252b8177f378d669";
    private static final String BASE_URL = "https://api.flickr.com/services/rest/";

    private final RestTemplate restTemplate = new RestTemplate();

    public String searchImages(String query, int page, int size) {
        String url = BASE_URL +
                "?method=flickr.photos.search" +
                "&api_key=" + API_KEY +
                "&text=" + query +
                "&page=" + page +
                "&per_page=" + size +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_m,owner_name";
        return restTemplate.getForObject(url, String.class);
    }

    public String getImageInfo(String id) {
        String url = BASE_URL +
                "?method=flickr.photos.getInfo" +
                "&api_key=" + API_KEY +
                "&photo_id=" + id +
                "&format=json" +
                "&nojsoncallback=1";
        return restTemplate.getForObject(url, String.class);
    }

    public String getImageSizes(String id) {
        String url = BASE_URL +
                "?method=flickr.photos.getSizes" +
                "&api_key=" + API_KEY +
                "&photo_id=" + id +
                "&format=json" +
                "&nojsoncallback=1";
        return restTemplate.getForObject(url, String.class);
    }
}
