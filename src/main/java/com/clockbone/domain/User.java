package com.clockbone.domain;

import java.io.Serializable;

/**
 * Created by qinjun on 2016/2/18.
 */
public class User implements Serializable{
    private String userName;
    private String passWord;
    private String roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
