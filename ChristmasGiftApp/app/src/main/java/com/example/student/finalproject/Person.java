package com.example.student.finalproject;

/**
 * Created by student on 6/28/16.
 */
public class Person {
    String name;
    int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
