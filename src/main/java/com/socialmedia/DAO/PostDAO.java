package com.socialmedia.DAO;

import com.socialmedia.entities.Post;
import com.socialmedia.home.Util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PostDAO {

    EntityManager em;

    public PostDAO(EntityManager em){
        this.em = em;
    }

    public List<Post> getPosts(String userName) {

        var tx = em.getTransaction();
        tx.begin();
        List <Post> posts = em.createQuery("SELECT p from POST p WHERE p.userName = '"+userName+"'").getResultList();
        System.out.println(posts.get(0).getContent());  //remove
        tx.commit();

        if (posts.isEmpty()){
            throw new NullPointerException("no such posts");
        }

        return posts;
    }

    public Post getPost(String postId){

        var tx = em.getTransaction();
        tx.begin();
        Post post = (Post) em.createQuery("SELECT p from POST p WHERE p.postId = '" +postId+"'").getSingleResult();
        tx.commit();
        return post;
    }

    public void deletePost(String postId, String email){
        var tx = em.getTransaction();
        tx.begin();
        em.createQuery("DELETE p from POST p WHERE p.postId = '"+postId+"' AND p.email = '"+email+"'").executeUpdate();
        tx.commit();
    }

    public String createPosts(String email, String postText){

        Post post = new Post(email,postText);
        var tx = em.getTransaction();
        tx.begin();
        em.persist(post);
        tx.commit();

        return post.getPostId();
    }

}
