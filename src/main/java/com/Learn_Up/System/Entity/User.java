package com.Learn_Up.System.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String bio;
    private String profilePicturePath;

    //lets make getter and setter

    public long getId(){
        return id;
    }
    public void setId(long userId){
        this.id=userId;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role=role;
    }

    public String getBio(){
        return bio;
    }
    public void setBio(String bio){
        this.bio=bio;
    }
    public String getProfilePicturePath(){
        return profilePicturePath;
    }
    public void setProfilePicturePath(String profilePicture){
        this.profilePicturePath=profilePicture;
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);

    }
}
