package com.alpha.whizwish.Models;

import java.util.ArrayList;

public class WhatsOnModel {

    private int userImage;
    private String userName;
    private int postImage;

    public WhatsOnModel() {
    }

    public WhatsOnModel(int userImage, String userName, int postImage) {
        this.userImage = userImage;
        this.userName = userName;
        this.postImage = postImage;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }
}
