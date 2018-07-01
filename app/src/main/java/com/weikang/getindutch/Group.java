package com.weikang.getindutch;

import java.util.HashMap;

public class Group {

    private String name;
    private String photoUrl;
    private HashMap<String, Long> members;

    public Group(){

    }

    public Group(String name, String photoUrl, HashMap<String, Long> members){
        this.name = name;
        this.photoUrl = photoUrl;
        this.members = members;
    }

    public String getName(){return name;}

    public String getPhotoUrl(){return photoUrl;}

    public HashMap<String, Long> getMembers(){return members;}

    public void setName(String name){this.name = name;}

    public void setPhotoUrl(String photoUrl){this.photoUrl = photoUrl;}

    //public void setMembers(ArrayList<String> members){this.members = members;}
}
