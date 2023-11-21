package com.socialmedia.DAO;

import java.util.List;
import com.socialmedia.entities.Profile;
import com.socialmedia.home.Util;
import jakarta.persistence.EntityManager;

public class ProfileDAO {
    EntityManager em;
    public ProfileDAO(EntityManager em){
        this.em = em;
    }
    public Profile logIn(String email, String password){

        if(!checkProfile(email)){
            throw new IllegalArgumentException("profile does not exist");
        }

        var tx = em.getTransaction();
        tx.begin();
        List <Profile> profiles = em.createQuery("SELECT p from PROFILE p WHERE p.email = '" +email+ "'AND p.password = '"+password+"'").getResultList();
        tx.commit();

        if(profiles.isEmpty()){
            throw new IllegalArgumentException("incorrect email or password");
        }

        return profiles.get(0);

    }

    public void CreateProfile(Profile profile){

        if(checkProfile(profile.getEmail())){
            throw new IllegalArgumentException("profile already exists");
        }

        var tx = em.getTransaction();
        tx.begin();
        em.persist(profile);
        tx.commit();
    }

    public void deleteProfile(EntityManager em, String email, String password){

        logIn(email,password);
        var tx = em.getTransaction();
        tx.begin();
        em.createQuery("DELETE * from m WHERE m.email = "+email+" AND m.password = "+password).executeUpdate();
        tx.commit();

    }

    public boolean checkProfile(String email){
        var tx = em.getTransaction();
        tx.begin();
        List profiles = em.createQuery("SELECT p from PROFILE p WHERE p.email = '"+ email+"'", Profile.class).getResultList();
        tx.commit();
        if(profiles.size()>0){
            return true;
        }
        return false;
    }
}
