package com.example.unicornstore.Utills;

public class Posts {

    private String username,Product,Phone,Location,Condition,Price,postImageUrl;

    public Posts() {
    }

    public Posts(String username, String product, String phone, String location, String condition, String price, String postImageUrl) {
        this.username = username;
        Product = product;
        Phone = phone;
        Location = location;
        Condition = condition;
        Price = price;
        this.postImageUrl = postImageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }
}
