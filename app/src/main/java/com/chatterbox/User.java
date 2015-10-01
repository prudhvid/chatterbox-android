package com.chatterbox;

/**
 * Created by menirus on 2/10/15.
 */
public class User {
    String _id;
    String name;
    Boolean online;

    public User(){
        _id="";
        name = "";
        online = false;
    }

    public User(String id, String nm, Boolean sts){
        _id = id;
        name = nm;
        online = sts;
    }

    public String getUsername(){
        return name;
    }

    public Boolean getOnlineStatus(){
        return online;
    }
}
