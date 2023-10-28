package com.socialmedia.home;

import com.socialmedia.DAO.PostDAO;
import com.socialmedia.DAO.ProfileDAO;
import com.socialmedia.entities.Post;
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

        System.out.println("------------------------------");
        System.out.println("WELCOME TO THE APP!");
        System.out.println("SELECT ONE OF THE OPTIONS BELOW TO PROCEED");
        System.out.println("TO SIGNUP PLEASE SELECT: "+OPTION_SIGNUP);
        System.out.println("TO LOGIN PLEASE SELECT: "+OPTION_LOGIN);

        ProfileDAO pdao = new ProfileDAO();
        Scanner sc = new Scanner(System.in);
        int input = Integer.parseInt(sc.nextLine());
        System.out.println("Enter account email");
        String email = sc.nextLine();
        System.out.println("<----------->");
        System.out.println("Enter account password");
        String password = sc.nextLine();
        System.out.println("<----------->");

        switch(input){
            case OPTION_SIGNUP:
                pdao.CreateProfile(new Profile(email,password));

            case OPTION_LOGIN:
                pdao.logIn(email,password);
        }

        Profile profile = new Profile();

        System.out.println("WHAT OPERATION WOULD YOU LIKE TO DO NOW");
        System.out.println("TO VIEW POSTS ENTER "+OPTION_VIEW_POSTS);
        System.out.println("TO CREATE A POST ENTER "+OPTION_CREATE_POST);
        System.out.println("TO SEARCH FOR POSTS ENTER "+OPTION_SEARCH_POST);
        System.out.println("TO SEARCH FOR A PROFILE ENTER "+OPTION_SEARCH_PROFILE);
        System.out.println("TO VIEW PERSONAL PROFILE ENTER "+OPTION_VIEW_PERSONAL_PROFILE);

        int operationInput = Integer.parseInt(sc.nextLine());


        if(operationInput==OPTION_VIEW_POSTS){
            var dao = getPostDAO();
            try{
                List<Post> posts = dao.getPosts(profile.getEmail());
                int count = 0;
                for (Post post : posts){
                    System.out.println("Post number :"+ count++);
                    System.out.println("Post ID :"+ post.getPostId());
                    System.out.println(post.getContent());
                    System.out.println("--------------------");
                }
            }catch (Exception e){
                System.out.println("an error occured");
                e.printStackTrace();
            }

        } else if (operationInput==OPTION_CREATE_POST ) {
            var dao = getPostDAO();
            System.out.println("Enter post text and hit enter");
            String postText = sc.nextLine();
            dao.createPosts(email,postText);

        } else if (operationInput==OPTION_SEARCH_POST ) {

        } else if (operationInput==OPTION_SEARCH_PROFILE ) {

        } else if (operationInput==OPTION_VIEW_PERSONAL_PROFILE){

        }



    }

    public static PostDAO getPostDAO(){
        return new PostDAO();
    }

    public static ProfileDAO getProfileDAO(){
        return new ProfileDAO();
    }


    static void logOut(){

    }
}
