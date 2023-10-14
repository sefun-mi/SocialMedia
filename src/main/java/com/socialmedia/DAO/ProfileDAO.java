package com.socialmedia.DAO;

import com.socialmedia.entities.Profile;
import com.socialmedia.home.Util;
import jakarta.persistence.EntityManager;

public class ProfileDAO {
    Util util = new Util();

    public void CreateProfile(Profile profile){
        EntityManager em = util.getEM();
        var tx = em.getTransaction();
        tx.begin();
        em.persist(profile);
        tx.commit();
        em.close();
    }

    public void deleteProfile(String email){

    }
}
