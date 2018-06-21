package com.alpha.whizwish.Models;

public class WishBoardModel {

    private int userIamge, productImage;
    private String cost, desc;

    public WishBoardModel() {
    }

    public WishBoardModel(int userIamge, int productImage, String cost, String desc) {
        this.userIamge = userIamge;
        this.productImage = productImage;
        this.cost = cost;
        this.desc = desc;
    }

    public int getUserIamge() {
        return userIamge;
    }

    public void setUserIamge(int userIamge) {
        this.userIamge = userIamge;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}


