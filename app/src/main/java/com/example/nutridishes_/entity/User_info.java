package com.example.nutridishes_.entity;

public class User_info {
    private int Info_ID ;
    private int User_ID ;
    private String phonenum ;
    private String address;
    private int age;
    private Integer gender;
    private double height;
    private double weight;
    private double bodyfat;
    private int goal;
    private int Prefer_ID;

    public User_info() {
    }

    public User_info(int Info_ID , int User_ID , Integer gender, int goal, double height,
                     double weight, int age, double bodyfat, String phonenum, String address, int Prefer_ID) {

        this.Info_ID = Info_ID;
        this.User_ID = User_ID;
        this.phonenum = phonenum;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.bodyfat = bodyfat;
        this.goal = goal;
        this.Prefer_ID = Prefer_ID;
    }
    //1
    public int getInfoId() {
        return Info_ID;
    }

    public void setInfoID(int Info_ID) {
        this.Info_ID = Info_ID;
    }
    //2
    public int getUserID() {
        return User_ID;
    }

    public void setUserID(int User_ID) {
        this.User_ID = User_ID;
    }
    //3
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
    //4
    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }
    //5
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    //6
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    //7
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    //8
    public double getBodyFat() {
        return bodyfat;
    }

    public void setBodyFat(double bodyfat) {this.bodyfat = bodyfat;}
    //9
    public String getPhoneNum() {return phonenum;}

    public void setPhoneNum(String phonenum) {this.phonenum = phonenum;}
    //10
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    //11
    public int getPreferID() {
        return Prefer_ID;
    }

    public void setPreferID(int Prefer_ID) {
        this.Prefer_ID = Prefer_ID;
    }
}
