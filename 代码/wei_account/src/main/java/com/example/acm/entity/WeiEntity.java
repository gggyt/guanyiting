package com.example.acm.entity;

/**
 * Created by ggg on 2019/4/9.
 */
public class WeiEntity {

    private String image;
    private String openId;

    public WeiEntity(String image, String openId) {
        this.image = image;
        this.openId = openId;
    }
    public String getImage() {
        return image;
    }

    public String getOpenId() {
        return openId;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

}
