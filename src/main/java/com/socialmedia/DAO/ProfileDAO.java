package com.socialmedia.DAO;

import java.util.List;
import com.socialmedia.entities.Profile;
import com.socialmedia.home.Util;
import jakarta.persistence.EntityManager;

public class ProfileDAO {
    Util util = new Util();

    public void logIn(String email, String password){

        if(!checkProfile(email)){
            throw new IllegalArgumentException("profile does not exist");
        }

        EntityManager em = util.getEM();
        var tx = em.getTransaction();
        tx.begin();
        List profiles = em.createQuery("SELECT * from m WHERE m.email = "+email+" AND m.password = "+password).getResultList();
        em.close();

        if(profiles.size()<1){
            throw new IllegalArgumentException("incorrect email or password");
        }

    }

    public void CreateProfile(Profile profile){

        if(checkProfile(profile.getEmail())){
            throw new IllegalArgumentException("profile already exists");
        }

        EntityManager em = util.getEM();
        var tx = em.getTransaction();
        tx.begin();
        em.persist(profile);
        tx.commit();
        em.close();
    }

    public void deleteProfile(String email, String password){

        logIn(email,password);
        EntityManager em = util.getEM();
        var tx = em.getTransaction();
        tx.begin();
        em.createQuery("DELETE * from m WHERE m.email = "+email+" AND m.password = "+password).executeUpdate();
        tx.commit();
        em.close();

    }

    public boolean checkProfile(String email){
        EntityManager em = util.getEM();
        var tx = em.getTransaction();
        tx.begin();
        List profiles = em.createQuery("SELECT * from PROFILE WHERE PROFILE.email = "+email).getResultList();

        if(profiles.size()>0){
            return true;
        }
        em.close();
        return false;
    }
}
