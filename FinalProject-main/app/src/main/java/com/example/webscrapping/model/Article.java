package com.example.webscrapping.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;
@Entity
public class Article implements Serializable {
    @PrimaryKey
    private int id;
    private String author;
    private String title;
    private String description;
    private String urlSource;
    private String imageUrl;
    private String content;
    private String date;

    public Article(int id, String author, String title, String description, String urlSource, String imageUrl, String content, String date) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.urlSource = urlSource;
        this.imageUrl = imageUrl;
        this.content = content;
        this.date = date;
    }
//    public Article(int id,String title,String imageUrl, String content) {
//        this.id = id;
//        this.author = author;
//        this.title = title;
//        this.imageUrl = imageUrl;
//        this.content = content;
//    }
//
//    public Article(int id,String title,String imageUrl, String content,String URLsource) {
//        this.id = id;
//        this.urlSource = URLsource;
//        this.title = title;
//        this.imageUrl = imageUrl;
//        this.content = content;
//    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlSource() {
        return urlSource;
    }

    public void setUrlSource(String urlSource) {
        this.urlSource = urlSource;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MyArticles{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", urlSource='" + urlSource + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
