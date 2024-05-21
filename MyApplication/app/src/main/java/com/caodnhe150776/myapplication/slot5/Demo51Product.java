package com.caodnhe150776.myapplication.slot5;

public class Demo51Product {
    private String id;
    private String Name;
    private double price;
    private int image;

    public Demo51Product() {
    }

    public Demo51Product(String id, String name, double price, int image) {
        this.id = id;
        Name = name;
        this.price = price;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
