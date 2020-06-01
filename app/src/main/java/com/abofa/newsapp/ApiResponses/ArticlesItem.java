
package com.abofa.newsapp.ApiResponses;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ArticlesItem {

    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;
    @SerializedName("description")
    private String description;
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("source")
    private SourcesItem source;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private String urlToImage;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public SourcesItem getSource() {
        return source;
    }

    public void setSource(SourcesItem source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

}
