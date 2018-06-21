package com.alpha.whizwish.Models;

public class TimelineModel {
    private String userName;
    private int userImage;
    private String userHome;
    private String postTime;
    private int postImage;
    private String postTitle, postContent;
    private int commentCount;

    public TimelineModel() {
    }

    public TimelineModel(String userName, int userImage, String userHome, String postTime, int postImage, String postTitle, String postContent, int commentCount) {
        this.userName = userName;
        this.userImage = userImage;
        this.userHome = userHome;
        this.postTime = postTime;
        this.postImage = postImage;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.commentCount = commentCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }

    public String getUserHome() {
        return userHome;
    }

    public void setUserHome(String userHome) {
        this.userHome = userHome;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
