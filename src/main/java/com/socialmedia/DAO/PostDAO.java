package com.socialmedia.DAO;

import com.socialmedia.home.Util;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PostDAO {

    Util util = new Util();

    public List getPosts(String email) throws Exception {
        EntityManager em =  util.getEM();
        var tx = em.getTransaction();
        List posts = em.createQuery("SELECT p from POSTS p WHERE p.email = '" +email+"'").getResultList();

        if (posts.isEmpty()){
            System.out.println("no such posts");
            throw new Exception();
        }

        return posts;
    }

    public void createPosts(String postText, String email){

    }

}
