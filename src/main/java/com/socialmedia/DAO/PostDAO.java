package com.socialmedia.DAO;

import com.socialmedia.entities.Post;
import com.socialmedia.home.Util;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PostDAO {

    Util util = new Util();

    public List getPosts(String email) { //edit, also, decide structure of posts
        EntityManager em =  util.getEM();
        List posts = em.createQuery("SELECT p from POST p WHERE p.email = '" +email+"'").getResultList();

        if (posts.isEmpty()){
            throw new NullPointerException("no such posts");
        }

        return posts;
    }

    public Post getPost(String postId){
        EntityManager em =  util.getEM();
        Post post = (Post) em.createQuery("SELECT p from POST p WHERE p.postId = '" +postId+"'").getSingleResult();
        return post;
    }

    public void createPosts(String email, String postText){
        EntityManager em =  util.getEM();
        var tx = em.getTransaction();
        tx.begin();
        em.persist(new Post(email,postText));
        tx.commit();
        em.close();
    }

}
