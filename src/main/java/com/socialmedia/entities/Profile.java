package com.socialmedia.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//import java.util.ArrayList;
@Entity(name = "PROFILE")
public class Profile {

    private String userId;
    private String userName;
    private String password;
    @Id
    private String email;


    public Profile(){
        this.userId = "0001";
    }

    public Profile(String email, String password){
        this.email = email;
        this.password = password;
        this.userId = "0002";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
