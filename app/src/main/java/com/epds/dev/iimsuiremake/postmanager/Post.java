package com.epds.dev.iimsuiremake.postmanager;

public class Post {
    private String post_text;
    private String post_dateTime;
    private String post_name;
    private int profilePic;
    private int post_img;

    public Post(String post_text, String post_dateTime, String post_name, int profilePic, int post_img) {
        this.post_text = post_text;
        this.post_dateTime = post_dateTime;
        this.post_name = post_name;
        this.profilePic = profilePic;
        this.post_img = post_img;
    }

    public String getPost_text() {
        return post_text;
    }

    public String getPost_dateTime() {
        return post_dateTime;
    }

    public String getPost_name() {
        return post_name;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public int getPost_img() {
        return post_img;
    }
}
