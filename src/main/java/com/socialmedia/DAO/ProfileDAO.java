package com.socialmedia.DAO;

import java.util.List;
import com.socialmedia.entities.Profile;
import com.socialmedia.home.Util;
import jakarta.persistence.EntityManager;

public class ProfileDAO {
    Util util = new Util();

    public void checkProfile(String email, String pasword){

    }

    public void CreateProfile(Profile profile){
        EntityManager em = util.getEM();
        var tx = em.getTransaction();
        tx.begin();
        List<Profile> profiles = em.createQuery("").getResultList();

        if(profiles.size()>0){
            throw new IllegalArgumentException("profile already exists");
        }

        em.persist(profile);
        tx.commit();
        em.close();
    }

    public void deleteProfile(String email){

    }

    public boolean checkProfile(String email){
        EntityManager em = util.getEM();
        var tx = em.getTransaction();
        tx.begin();
        List<Profile> profiles = em.createQuery("").getResultList();

        if(profiles.size()>0){
            return true;
        }
        em.close();
        return false;
    }
}
