package com.example.nutridishes_.entity;

import java.sql.Date;

public class User {

    private int id;
    private String email;
    private String password;
    private String username;
    private Date createdate;

    public User() {
    }

    public User(int id, String email, String password, String username,Date createdate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.createdate = createdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

}
