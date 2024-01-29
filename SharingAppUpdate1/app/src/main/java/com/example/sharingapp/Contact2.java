package com.example.sharingapp;

import java.util.UUID;
public class Contact {
    private String userName;
    private String email;
    private String id;

    public Contact(String userName, String email, String id){
        this.userName=userName;
        this.email=email;
        if(id==null){
            setId();
        }
        else {
            updateId(id);
        }
    }
    public void setId(){

        this.id= UUID.randomUUID().toString();
    }
    public String getId(){

        return this.id;
    }
    public void updateId(String id){

        this.id=id;
    }
    public void setUserName(String userName){

        this.userName=userName;
    }
    public String getUsername(){

        return this.userName;
    }
    public void setEmail(String email){

        this.email=email;
    }
    public String getEmail(){

        return this.email;
    }
}
