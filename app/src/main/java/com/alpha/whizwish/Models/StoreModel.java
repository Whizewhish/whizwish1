package com.alpha.whizwish.Models;

public class StoreModel {

    private int store_image;
    private String store_name;

    public StoreModel() {
    }

    public StoreModel(int store_image, String store_name) {
        this.store_image = store_image;
        this.store_name = store_name;
    }

    public int getStore_image() {
        return store_image;
    }

    public void setStore_image(int store_image) {
        this.store_image = store_image;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }
}
