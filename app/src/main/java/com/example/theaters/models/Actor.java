package com.example.theaters.models;

public class Actor {
    private String name;
    private String imgUrl;

    public Actor(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
