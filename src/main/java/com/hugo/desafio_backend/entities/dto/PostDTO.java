package com.hugo.desafio_backend.entities.dto;

import java.io.Serializable;

import com.hugo.desafio_backend.entities.Post;

public class PostDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private String title;
    private String description;
    private String body;
    
    public PostDTO(){
    }

    public PostDTO(Post post) {
        title = post.getTitle();
        description = post.getDescription();
        body = post.getBody();
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    
}
