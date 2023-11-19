package com.socialmedia.DAO;

import com.socialmedia.entities.Post;
import com.socialmedia.home.Util;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PostDAO {

    Util util = new Util();

    public List getPosts(String email) {

        EntityManager em =  util.getEM();
        System.out.println("---------------------------wait for return");
        var tx = em.getTransaction();
        tx.begin();
        List <Post> posts = em.createQuery("SELECT p from POST p WHERE p.email = '"+email+"'").getResultList();
        System.out.println(posts.get(0).getContent());
        tx.commit();
        em.close();

//        EntityManager em =  util.getEM();
//        var tx = em.getTransaction();
//        tx.begin();
//        System.out.println("chai oh-----------"+email);
//        String query = "SELECT p from PROFILE p ";
//        System.out.println(query);
//        List <Post> posts = em.createQuery(query).getResultList();
//        tx.commit();
//        em.close();

        if (posts.isEmpty()){
            //throw new NullPointerException("no such posts");
            System.out.println("nothing");
        }

        return posts;
    }

    public Post getPost(String postId){
        EntityManager em =  util.getEM();
        var tx = em.getTransaction();
        tx.begin();
        Post post = (Post) em.createQuery("SELECT p from POST p WHERE p.postId = '" +postId+"'").getSingleResult();
        tx.commit();
        em.close();
        return post;
    }

    public void createPosts(String email, String postText){
        EntityManager em =  util.getEM();
        var tx = em.getTransaction();
        tx.begin();
        em.persist(new Post(email,postText));
        tx.commit();
        em.close();

        em =  util.getEM();
        System.out.println("---------------------------wait for return");
        tx = em.getTransaction();
        tx.begin();
        List <Post> posts = em.createQuery("SELECT p from POST p WHERE p.email = '"+email+"'").getResultList();
        System.out.println(posts.get(0).getContent());
        tx.commit();
        em.close();
    }

}
