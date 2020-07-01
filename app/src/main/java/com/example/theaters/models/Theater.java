package com.example.theaters.models;

import java.io.Serializable;

public class Theater implements Serializable {
    private int imageId;
    private String name;
    private String address;
    private String site;
    private String vk;
    private String phone;

    public Theater(int imageId, String name, String address, String site, String vk, String phone) {
        this.imageId = imageId;
        this.name = name;
        this.address = address;
        this.site = site;
        this.vk = vk;
        this.phone = phone;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getSite() {
        return site;
    }

    public String getVk() {
        return vk;
    }

    public String getPhone() {
        return phone;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
