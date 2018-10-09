package com.junhuayu.yanxuandemo.beans;

import java.util.List;

public class Category {
    private String id;
    private String name;
    private String image;
    private List<Category> child;
    private List<CateAd> cateAds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Category> getChild() {
        return child;
    }

    public void setChild(List<Category> child) {
        this.child = child;
    }

    public List<CateAd> getCateAds() {
        return cateAds;
    }

    public void setCateAds(List<CateAd> cateAds) {
        this.cateAds = cateAds;
    }
}
