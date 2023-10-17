package com.socialmedia.home;

import com.socialmedia.entities.Profile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class App {

    public static final int OPTION_SIGNUP = 1;
    public static final int OPTION_LOGIN = 2;

    public static void main(String [] args){
        System.out.println("------------------------------");
        System.out.println("WELCOME TO THE APP!");
        System.out.println("SELECT ONE OF THE OPTIONS BELOW TO PROCEED");
        System.out.println("TO SIGNUP PLEASE SELECT: "+OPTION_SIGNUP);
        System.out.println("TO LOGIN PLEASE SELECT: "+OPTION_LOGIN);

    }

    public void query(Object obj){

    }

    static void feedMenu(){

    }

    static void searchMenu(){

    }

    static void profileMenu(){

    }

    static void createMenu(){

    }

    static void logOut(){

    }
}
