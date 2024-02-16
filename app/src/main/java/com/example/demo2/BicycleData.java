package com.example.demo2;

public class BicycleData {
    String userNo;
    String adminNo;
    private String bicycleName;
    private String bicycleDesc;

    public BicycleData(String userNo, String adminNo, String bicycleName, String bicycleDesc, String bicycleLocation, String bicycleRupees, String bicycleImage) {
        this.userNo = userNo;
        this.adminNo = adminNo;
        this.bicycleName = bicycleName;
        this.bicycleDesc = bicycleDesc;
        this.bicycleLocation = bicycleLocation;
        this.bicycleRupees = bicycleRupees;
        this.bicycleImage = bicycleImage;
    }

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

    private String bicycleLocation;
    private String bicycleRupees;
    private String bicycleImage;
    private String bicycleKey;
    //private String bicycleAdmin;

    public BicycleData(){}

//    public BicycleData(String bicycleName, String bicycleDesc, String bicycleLocation, String bicycleRupees, String bicycleImage) {
//        this.bicycleName = bicycleName;
//        this.bicycleDesc = bicycleDesc;
//        this.bicycleLocation = bicycleLocation;
//        this.bicycleRupees = bicycleRupees;
//        this.bicycleImage = bicycleImage;
//        //this.bicycleAdmin = bicycleAdmin;
//    }

//    public String getBicycleAdmin() {
//        return bicycleAdmin;
//    }
//
//    public void setBicycleAdmin(String bicycleAdmin) {
//        this.bicycleAdmin = bicycleAdmin;
//    }

    public void setBicycleName(String bicycleName) {
        this.bicycleName = bicycleName;
    }

    public void setBicycleDesc(String bicycleDesc) {
        this.bicycleDesc = bicycleDesc;
    }

    public void setBicycleLocation(String bicycleLocation) {
        this.bicycleLocation = bicycleLocation;
    }

    public void setBicycleRupees(String bicycleRupees) {
        this.bicycleRupees = bicycleRupees;
    }

    public void setBicycleImage(String bicycleImage) {
        this.bicycleImage = bicycleImage;
    }

    public void setBicycleKey(String bicycleKey) {
        this.bicycleKey = bicycleKey;
    }

    public String getBicycleName() {
        return bicycleName;
    }

    public String getBicycleDesc() {
        return bicycleDesc;
    }

    public String getBicycleLocation() {
        return bicycleLocation;
    }

    public String getBicycleRupees() {
        return bicycleRupees;
    }

    public String getBicycleImage() {
        return bicycleImage;
    }

    public String getBicycleKey() {
        return bicycleKey;
    }

}
