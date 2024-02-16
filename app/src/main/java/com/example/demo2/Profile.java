package com.example.demo2;

public class Profile {
    String name;
    String photo;
    String email;
    String phoneNo;

    public String getPhoto() {
        return photo;
    }
public Profile()
{}
    public Profile(String name, String photo, String email, String phoneNo, String address) {
        this.name = name;
        this.photo = photo;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public Profile(String name, String email, String phoneNo, String address) {
//        this.name = name;
//        this.email = email;
//        this.phoneNo = phoneNo;
//        this.address = address;
//    }
}
