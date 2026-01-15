package com.example.demo.dto;

public class ImageDetailDto {

    private String id;
    private String title;
    private String author;
    private String description;
    private String tags;
    private String imageUrl;

    public ImageDetailDto() {
    }

    public ImageDetailDto(String id, String title, String author,
                          String description, String tags, String imageUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.tags = tags;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
