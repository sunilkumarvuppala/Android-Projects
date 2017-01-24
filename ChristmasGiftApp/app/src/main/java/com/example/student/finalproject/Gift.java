package com.example.student.finalproject;

/**
 * Created by student on 6/28/16.
 */
public class Gift {
    String imageUrl,gift;
    int price;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "imageUrl='" + imageUrl + '\'' +
                ", gift='" + gift + '\'' +
                ", price=" + price +
                '}';
    }
}
