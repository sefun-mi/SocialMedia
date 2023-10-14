package com.socialmedia.home;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Util {
    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("salted.socialMedia");

    public EntityManager getEM(){
        return emf.createEntityManager();
    }

}
