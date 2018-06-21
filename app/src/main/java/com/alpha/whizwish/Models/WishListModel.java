package com.alpha.whizwish.Models;

public class WishListModel {
    private int productImage;
    private String cost, desc;

    public WishListModel() {
    }
    public WishListModel(int productImage, String cost, String desc) {

        this.productImage = productImage;
        this.cost = cost;
        this.desc = desc;
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
