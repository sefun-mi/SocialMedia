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
    public static final int OPTION_VIEW_POSTS = 3;
    public static final int OPTION_CREATE_POST = 4;
    public static final int OPTION_SEARCH_POST = 5;
    public static final int OPTION_SEARCH_PROFILE = 6;
    public static final int OPTION_VIEW_PERSONAL_PROFILE = 7;

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println("again");
        String input2 = sc.nextLine();
        System.out.println("againnn");
        String input3 = sc.nextLine();

//        System.out.println("------------------------------");
//        System.out.println("WELCOME TO THE APP!");
//        System.out.println("SELECT ONE OF THE OPTIONS BELOW TO PROCEED");
//        System.out.println("TO SIGNUP PLEASE SELECT: "+OPTION_SIGNUP);
//        System.out.println("TO LOGIN PLEASE SELECT: "+OPTION_LOGIN);
//
//        ProfileDAO pdao = new ProfileDAO();
//        Scanner sc = new Scanner(System.in);
//        int input = sc.nextInt();
//        System.out.println("Enter account email");
//        String email = sc.nextLine();
//        System.out.println("<----------->");
//        System.out.println("Enter account password");
//        String password = sc.nextLine();
//        System.out.println("<----------->");
//
//        switch(input){
//            case 1:
//                pdao.CreateProfile(new Profile(email,password));
//
//            case 2:
//                pdao.logIn(email,password);
//        }
//
//        System.out.println("WHAT OPERATION WOULD YOU LIKE TO DO NOW");
//        System.out.println("TO VIEW POSTS ENTER "+OPTION_VIEW_POSTS);
//        System.out.println("TO CREATE A POST ENTER "+OPTION_CREATE_POST);
//        System.out.println("TO SEARCH FOR POSTS ENTER "+OPTION_SEARCH_POST);
//        System.out.println("TO SEARCH FOR A PROFILE ENTER "+OPTION_SEARCH_PROFILE);
//        System.out.println("TO VIEW PERSONAL PROFILE ENTER "+OPTION_VIEW_PERSONAL_PROFILE);

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
