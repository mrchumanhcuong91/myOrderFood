package com.example.orderfood.Model;

public class DrinkShop {
    String name, description, urlImage;
    int discount, price;


    public DrinkShop(String name, String description, String urlImage,
            int discount, int price) {
        this.name = name;
        this.description = description;
        this.urlImage = urlImage;
        this.discount = discount;
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
