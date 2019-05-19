package com.haipiao.persist.entity;

import com.haipiao.persist.enums.Gender;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hp_user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "password_digest")
    private byte[] passwordDigest;

    @Column(name = "nick_name")
    private String userName;

    private String email;

    private String phone;

    @Column(name = "tc_accepted")
    private boolean tcAccepted;

    private Date birthday;

    private Gender gender;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "address_1")
    private String address1;

    @Column(name = "address_2")
    private String address2;

    private String city;

    private String region;

    private String country;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "profile_img_url")
    private String profileImgUrl;

    private String organization;

    private String signature;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public byte[] getPasswordDigest() {
        return passwordDigest;
    }

    public void setPasswordDigest(byte[] passwordDigest) {
        this.passwordDigest = passwordDigest;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isTcAccepted() {
        return tcAccepted;
    }

    public void setTcAccepted(boolean tcAccepted) {
        this.tcAccepted = tcAccepted;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
