package com.alpha.whizwish.Models;

public class AlertModel {


    private int imageId;
    private String alertContent;
    public AlertModel() {
    }
    public AlertModel(int imageId, String alertContent) {
        this.imageId = imageId;
        this.alertContent = alertContent;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getAlertContent() {
        return alertContent;
    }

    public void setAlertContent(String alertContent) {
        this.alertContent = alertContent;
    }
}