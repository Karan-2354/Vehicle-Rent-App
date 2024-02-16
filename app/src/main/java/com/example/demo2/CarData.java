package com.example.demo2;

public class CarData {
    String userNo;
    String adminNo;
    private String carName;
    private String carDesc;
    private String carLocation;
    private String carRupees;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(String adminNo) {
        this.adminNo = adminNo;
    }

    private String carImage;

    public String getCarKey() {
        return carKey;
    }

    public void setCarKey(String carKey) {
        this.carKey = carKey;
    }

    private String carKey;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarDesc() {
        return carDesc;
    }

    public void setCarDesc(String carDesc) {
        this.carDesc = carDesc;
    }

    public String getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(String carLocation) {
        this.carLocation = carLocation;
    }

    public String getCarRupees() {
        return carRupees;
    }

    public void setCarRupees(String carRupees) {
        this.carRupees = carRupees;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public CarData(String userNo, String adminNo, String carName, String carDesc, String carLocation, String carRupees, String carImage) {
        this.userNo = userNo;
        this.adminNo = adminNo;
        this.carName = carName;
        this.carDesc = carDesc;
        this.carLocation = carLocation;
        this.carRupees = carRupees;
        this.carImage = carImage;
    }

    public CarData() {
    }


}
