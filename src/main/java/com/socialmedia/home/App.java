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
    public static final int OPTION_EXIT = 9;
    private static boolean isLoggedIn;
    private static boolean isExiting;
    private static final Scanner sc;

    static {
        sc = new Scanner(System.in);
    }
    public static void main(String [] args){

        Profile profile = startMenu();
        System.out.println("______________well that worked");

        while(isLoggedIn){
            System.out.println("WHAT OPERATION WOULD YOU LIKE TO DO NOW");
            System.out.println("TO VIEW POSTS ENTER "+OPTION_VIEW_POSTS);
            System.out.println("TO CREATE A POST ENTER "+OPTION_CREATE_POST);
            System.out.println("TO SEARCH FOR POSTS ENTER "+OPTION_SEARCH_POST);
            System.out.println("TO SEARCH FOR A PROFILE ENTER "+OPTION_SEARCH_PROFILE);
            System.out.println("TO VIEW PERSONAL PROFILE ENTER "+OPTION_VIEW_PERSONAL_PROFILE);
            System.out.println("TO EXIT THE APPLICATION ENTER "+OPTION_EXIT);

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
                System.out.println("Type post text and hit enter");
                String postText = sc.nextLine();
                dao.createPosts(profile.getEmail(), postText);

            } else if (operationInput==OPTION_SEARCH_POST ) {
                var dao = getPostDAO();
                System.out.println("Type postId and hit enter");
                String postId = sc.nextLine();
                Post post = dao.getPost(postId);
                System.out.println("Post ID :"+ post.getPostId());
                System.out.println(post.getContent());
                System.out.println("--------------------");

            } else if (operationInput==OPTION_SEARCH_PROFILE ) {
                var dao = getProfileDAO();
                System.out.println("Type username and hit enter");
                String userName = sc.nextLine();
                //TODO

            } else if (operationInput==OPTION_VIEW_PERSONAL_PROFILE){
                var dao = getPostDAO();
                List <Post> posts = dao.smth(profile.getEmail());
                System.out.println("Username is: "+ profile.getUserName());
                System.out.println("User email is: "+ profile.getEmail());
                System.out.println("User's posts are: ");
                System.out.println("<-------------------->");
//                for (Post post: posts){
//                    System.out.println("Post ID :"+ post.getPostId());
//                    System.out.println(post.getContent());
//                    System.out.println("--------------------");
//                }
                System.out.println("<-------------------->");
            }
            else if(operationInput==OPTION_EXIT){
                isLoggedIn = false;
            }
        }

    }

    public static Profile startMenu(){
        int input = 0;
        ProfileDAO pdao = new ProfileDAO();

        while(true){
            System.out.println("------------------------------");
            System.out.println("WELCOME TO THE APP!");
            System.out.println("SELECT ONE OF THE OPTIONS BELOW TO PROCEED");
            System.out.println("TO SIGNUP PLEASE SELECT: "+OPTION_SIGNUP);
            System.out.println("TO LOGIN PLEASE SELECT: "+OPTION_LOGIN);
            input = Integer.parseInt(sc.nextLine());

            System.out.println("Enter account email");
            String email = sc.nextLine();
            System.out.println("<----------->");
            System.out.println("Enter account password");
            String password = sc.nextLine();
            System.out.println("<----------->");

            switch(input){
                case OPTION_SIGNUP:
                    System.out.println(OPTION_SIGNUP);
                    pdao.CreateProfile(new Profile(email,password));
                    continue;

                case OPTION_LOGIN:
                    System.out.println(OPTION_LOGIN);
                    var profile = pdao.logIn(email,password);
                    isLoggedIn = true;
                    return profile;
            }

        }
    }

    public static void loggedInMenu(){

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
