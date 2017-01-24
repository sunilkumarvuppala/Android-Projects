package com.example.sunil.hw03;

import java.io.Serializable;

/**
 * Created by Sunil on 13-06-2016.
 */
public class App implements Serializable{
    String id;
    String appTitle, devName, url, smallImage, largeImage;
    String price;
    String releaseDate,category;

    public App(String id, String appTitle, String devName, String largeImage, String price, String releaseDate, String category) {
        this.id = id;
        this.appTitle = appTitle;
        this.devName = devName;
        this.largeImage = largeImage;
        this.price = price;
        this.releaseDate = releaseDate;
        this.category = category;
    }

    public App() {
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id ;

    }
}
