package com.socialmedia.home;

import com.socialmedia.DAO.ProfileDAO;
import com.socialmedia.entities.Profile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

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
        ProfileDAO pdao = new ProfileDAO();
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        switch (input){
            case 1:
                System.out.println("Enter account email");
                String email = sc.nextLine();
                System.out.println("Enter account password");
                String password = sc.nextLine();
                pdao.CreateProfile(new Profile(email,password));

        }

    }

    static void createMenu(){

    }

    static void logOut(){

    }
}
