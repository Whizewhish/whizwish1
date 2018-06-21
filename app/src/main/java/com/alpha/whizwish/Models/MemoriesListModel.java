package com.alpha.whizwish.Models;

public class MemoriesListModel {

    private String userName, city;
    private int userIamge;
    private int postImage, postTitle, postDesc, postTime;
    private boolean isLiked;

    public MemoriesListModel() {
    }

    public MemoriesListModel(String userName, String city, int userIamge, int postImage, int postTitle, int postDesc, int postTime, boolean isLiked) {
        this.userName = userName;
        this.city = city;
        this.userIamge = userIamge;
        this.postImage = postImage;
        this.postTitle = postTitle;
        this.postDesc = postDesc;
        this.postTime = postTime;
        this.isLiked = isLiked;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getUserIamge() {
        return userIamge;
    }

    public void setUserIamge(int userIamge) {
        this.userIamge = userIamge;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }

    public int getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(int postTitle) {
        this.postTitle = postTitle;
    }

    public int getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(int postDesc) {
        this.postDesc = postDesc;
    }

    public int getPostTime() {
        return postTime;
    }

    public void setPostTime(int postTime) {
        this.postTime = postTime;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
