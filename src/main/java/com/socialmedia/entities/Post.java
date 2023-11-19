package com.socialmedia.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Random;


//import java.util.ArrayList;

@Entity(name = "POST")
public class Post {
    private String email;
    @Id
    private String postId;
    private String content;
//    private ArrayList <Comment> comments;


    public Post() {
    }

    public Post(String email, String content) {
        Random rand = new Random();
        this.email = email;
        this.postId = String.valueOf(rand.nextInt(100,999));
        System.out.println("postid is: "+postId);
        this.content = content;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
