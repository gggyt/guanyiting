package com.example.acm.view;

import com.example.acm.entity.User;

public class UserView {
    private int uId;
    private String username;
    private String realname;
    private int grade;
    private int classNum;
    private String image;
    private int auth;

    public UserView(User user) {
        this.uId = user.getUserId();
        this.username = user.getUsername();
        this.realname = user.getRealname();
        if (user.getGrade()!=null) {
            this.grade = user.getGrade();
        }
        if (user.getClassNum()!=null) {
            this.classNum = user.getClassNum();
        }
        if (user.getImage()!=null) {
            this.image = user.getImage();
        }
        this.auth = user.getAuth();
    }
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public int getAuth() {
        return auth;
    }
}
