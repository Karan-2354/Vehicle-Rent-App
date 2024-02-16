package com.example.demo2;

public class BikeData {
    String userNo;
    String adminNo;

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

    public BikeData(String userNo, String adminNo, String bikeName, String bikeDesc, String bikeLocation, String bikeRupees, String bikeImage) {
        this.userNo = userNo;
        this.adminNo = adminNo;
        this.bikeName = bikeName;
        this.bikeDesc = bikeDesc;
        this.bikeLocation = bikeLocation;
        this.bikeRupees = bikeRupees;
        this.bikeImage = bikeImage;
    }

    public BikeData(){}
//    public BikeData(String bikeName, String bikeDesc, String bikeLocation, String bikeRupees, String bikeImage) {
//        this.bikeName = bikeName;
//        this.bikeDesc = bikeDesc;
//        this.bikeLocation = bikeLocation;
//        this.bikeRupees = bikeRupees;
//        this.bikeImage = bikeImage;
//    }

    private String bikeName;
    private String bikeDesc;
    private String bikeLocation;
    private String bikeRupees;
    private String bikeImage;
    private String bikeKey;

    public String getBikeName() {
        return bikeName;
    }

    public String getBikeDesc() {
        return bikeDesc;
    }

    public String getBikeLocation() {
        return bikeLocation;
    }

    public String getBikeRupees() {
        return bikeRupees;
    }

    public String getBikeImage() {
        return bikeImage;
    }

    public String getBikeKey() {
        return bikeKey;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public void setBikeDesc(String bikeDesc) {
        this.bikeDesc = bikeDesc;
    }

    public void setBikeLocation(String bikeLocation) {
        this.bikeLocation = bikeLocation;
    }

    public void setBikeRupees(String bikeRupees) {
        this.bikeRupees = bikeRupees;
    }

    public void setBikeImage(String bikeImage) {
        this.bikeImage = bikeImage;
    }

    public void setBikeKey(String bikeKey) {
        this.bikeKey = bikeKey;
    }
}
