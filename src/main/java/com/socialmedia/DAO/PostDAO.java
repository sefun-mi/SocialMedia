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

    public List getPosts(String email) {

        var tx = em.getTransaction();
        tx.begin();
        List <Post> posts = em.createQuery("SELECT p from POST p WHERE p.email = '"+email+"'").getResultList();
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

    public void createPosts(String email, String postText){

        var tx = em.getTransaction();
        tx.begin();
        em.persist(new Post(email,postText));
        tx.commit();

    }

//    public List<Post> smth(String email){
//        EntityManager em =  util.getEM();
//        var tx = em.getTransaction();
//        em =  util.getEM();
//        System.out.println("---------------------------wait for return");
//        tx = em.getTransaction();
//        tx.begin();
////        List <Post> posts = em.createQuery("SELECT p from POST p WHERE p.email = '"+email+"'").getResultList();
////        System.out.println(posts.get(0).getContent());
//        em.persist(new Post("iv","blahblah"));
//        System.out.println("hope this works");
//        List <Post> posts = em.createQuery("SELECT p from POST p WHERE p.email = 'iv'").getResultList();
//
//        System.out.println("_____________________________________here they are");
//        int count = 0;
//        for(Post post : posts){
//            System.out.println(count++);
//            System.out.println(post.getContent());
//        }
//        System.out.println(posts.get(0).getContent());
//        tx.commit();
//        em.close();
//       return null;
//    }

}
