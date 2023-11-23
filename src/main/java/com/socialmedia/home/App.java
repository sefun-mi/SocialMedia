package com.socialmedia.home;

import com.socialmedia.DAO.PostDAO;
import com.socialmedia.DAO.ProfileDAO;
import com.socialmedia.entities.Post;
import com.socialmedia.entities.Profile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.IllegalFormatFlagsException;
import java.util.List;
import java.util.Scanner;

public class App {

    public static final int OPTION_EXIT = 0;
    private static final int OPTION_SIGNUP = 1;
    private static final int OPTION_LOGIN = 2;
    public static final int OPTION_VIEW_POSTS = 3;
    public static final int OPTION_CREATE_POST = 4;
    public static final int OPTION_DELETE_POST = 5;
    public static final int OPTION_SEARCH_POST = 6;
    public static final int OPTION_SEARCH_PROFILE = 7;
    public static final int OPTION_VIEW_PERSONAL_PROFILE = 8;
    public static final int OPTION_LOGOUT = 9;
    private static boolean isLoggedIn;
    private static boolean isRunning;
    private static final Scanner sc;
    private static final EntityManager em;
    private static PostDAO postDAO;
    private static ProfileDAO profileDAO;

    static {
        sc = new Scanner(System.in);
        em = new Util().getEM();
    }
    public static void main(String [] args){

        isRunning = true;
        while(isRunning){
            Profile profile = startMenu();
            mainMenu(profile);
        }


    }

    public static Profile startMenu(){
        int input = 0;
        ProfileDAO pdao = new ProfileDAO(em);
        while(isRunning){
            System.out.println("------------------------------");
            System.out.println("WELCOME TO THE APP!");
            System.out.println("SELECT ONE OF THE OPTIONS BELOW TO PROCEED");
            System.out.println("TO SIGNUP PLEASE SELECT: "+OPTION_SIGNUP);
            System.out.println("TO LOGIN PLEASE SELECT: "+OPTION_LOGIN);
            System.out.println("TO EXIT PLEASE SELECT: "+OPTION_EXIT);
            input = Integer.parseInt(sc.nextLine());

            String userName, email, password;
            switch(input){
                case OPTION_SIGNUP:
                    System.out.println("Enter account email");
                    email = sc.nextLine();
                    System.out.println("<----------->");
                    System.out.println("Enter username");
                    userName = sc.nextLine();
                    System.out.println("<----------->");
                    System.out.println("Enter account password");
                    password = sc.nextLine();
                    System.out.println("<----------->");
                    pdao.CreateProfile(new Profile(userName,email,password));
                    break;

                case OPTION_LOGIN:
                    System.out.println("Enter account email");
                    email = sc.nextLine();
                    System.out.println("<----------->");
                    System.out.println("Enter account password");
                    password = sc.nextLine();
                    System.out.println("<----------->");
                    var profile = pdao.logIn(email,password);
                    isLoggedIn = true;
                    return profile;

                case OPTION_EXIT:
                    exit();
            }

        }
        return null;
    }


    public static void mainMenu(Profile profile){
        while(isLoggedIn){
            System.out.println("WHAT OPERATION WOULD YOU LIKE TO DO NOW");
            System.out.println("TO VIEW POSTS ENTER "+OPTION_VIEW_POSTS);
            System.out.println("TO CREATE A POST ENTER "+OPTION_CREATE_POST);
            System.out.println("TO SEARCH FOR POSTS ENTER "+OPTION_SEARCH_POST);
            System.out.println("TO SEARCH FOR A PROFILE ENTER "+OPTION_SEARCH_PROFILE);
            System.out.println("TO VIEW PERSONAL PROFILE ENTER "+OPTION_VIEW_PERSONAL_PROFILE);
            System.out.println("TO LOG OUT OF THE APPLICATION ENTER "+OPTION_LOGOUT);

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
                String postId = dao.createPosts(profile.getEmail(), postText);
                System.out.println("Creation successful, post ID is: "+postId);

            } else if (operationInput==OPTION_DELETE_POST ) {
                var dao = getPostDAO();
                System.out.println("Type post id and hit enter");
                String postId = sc.nextLine();
                dao.deletePost(postId, profile.getEmail());
                System.out.println("post with post ID is: "+postId+" deleted successfully");

            } else if (operationInput==OPTION_SEARCH_POST ) {
                var dao = getPostDAO();
                System.out.println("Type postId and hit enter");
                String postId = sc.nextLine().trim();
                Post post = dao.getPost(postId);
                System.out.println("Post ID :"+ post.getPostId());
                System.out.println(post.getContent());
                System.out.println("--------------------");

            } else if (operationInput==OPTION_SEARCH_PROFILE ) {
                var dao = getProfileDAO();
                System.out.println("Type username and hit enter");
                String userName = sc.nextLine();
                Profile searchedProfile = dao.searchProfile(userName);
                System.out.println("Username is: "+ searchedProfile.getUserName());
                System.out.println("User email is: "+ searchedProfile.getEmail());
                System.out.println("User's posts are: ");
                System.out.println("<-------------------->");
                for (Post post: getPostDAO().getPosts(userName)){
                    System.out.println("Post ID :"+ post.getPostId());
                    System.out.println(post.getContent());
                    System.out.println("--------------------");
                }
                System.out.println("<-------------------->");

            } else if (operationInput==OPTION_VIEW_PERSONAL_PROFILE){
                var dao = getPostDAO();
                List <Post> posts = dao.getPosts(profile.getEmail());
                System.out.println("Username is: "+ profile.getUserName());
                System.out.println("User email is: "+ profile.getEmail());
                System.out.println("User's posts are: ");
                System.out.println("<-------------------->");
                for (Post post: posts){
                    System.out.println("Post ID :"+ post.getPostId());
                    System.out.println(post.getContent());
                    System.out.println("--------------------");
                }
                System.out.println("<-------------------->");
            }
            else if(operationInput==OPTION_LOGOUT){
                isLoggedIn = false;
            }
        }
    }

    public static PostDAO getPostDAO(){
        if(postDAO == null){
            return new PostDAO(em);
        }

        return postDAO;
    }

    public static ProfileDAO getProfileDAO(){
        if(profileDAO == null){
            return new ProfileDAO(em);
        }

        return profileDAO;
    }

    static void exit(){
        if(isLoggedIn){
            System.out.println("please log out first");
        }
        em.close();
        isRunning = false;
    }
}
