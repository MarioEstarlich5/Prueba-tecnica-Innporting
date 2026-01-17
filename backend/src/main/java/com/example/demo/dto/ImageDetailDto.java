package com.example.demo.dto;

public class ImageDetailDto {

    private String id;
    private String title;
    private String author;
    private String description;
    private String tags;
    private String imageUrl;
    private String downloadUrl;
    private int width;
    private int height;

    public ImageDetailDto() {}

    public ImageDetailDto(String id, String title, String author,
                          String description, String tags, String imageUrl,
                          String downloadUrl, int width, int height) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.tags = tags;
        this.imageUrl = imageUrl;
        this.downloadUrl = downloadUrl;
        this.width = width;
        this.height = height;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getDescription() { return description; }
    public String getTags() { return tags; }
    public String getImageUrl() { return imageUrl; }
    public String getDownloadUrl() { return downloadUrl; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setDescription(String description) { this.description = description; }
    public void setTags(String tags) { this.tags = tags; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setDownloadUrl(String downloadUrl) { this.downloadUrl = downloadUrl; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
}
