package com.haipiao.entity;

import com.haipiao.annotation.DBField;
import com.haipiao.annotation.TableName;
import com.haipiao.constant.RDBMSTables.HpUser;
import com.haipiao.enums.UserGender;
import lombok.Data;

import java.util.Date;

@Data
@TableName(name = HpUser.NAME)
public class User extends BaseEntity {

    @DBField(name = HpUser.USER_ID, nonnull = true, primaryKey = true)
    private String userId;

    @DBField(name = HpUser.PASSWORD_DIGEST)
    private byte[] passwordDigest;

    @DBField(name = HpUser.USER_NAME, nonnull = true)
    private String userName;

    @DBField(name = HpUser.EMAIL, nonnull = true)
    private String email;

    @DBField(name = HpUser.PHONE)
    private String phone;

    @DBField(name = HpUser.TC_ACCEPTED, nonnull = true)
    private boolean tcAccepted;

    @DBField(name = HpUser.BIRTHDAY)
    private Date birthday;

    @DBField(name = HpUser.GENDER, nonnull = true)
    private UserGender gender;

    @DBField(name = HpUser.CANONICAL_NAME)
    private String canonicalName;

    @DBField(name = HpUser.ADDRESS_1)
    private String address1;

    @DBField(name = HpUser.ADDRESS_2)
    private String address2;

    @DBField(name = HpUser.CITY)
    private String city;

    @DBField(name = HpUser.REGION)
    private String region;

    @DBField(name = HpUser.COUNTRY)
    private String country;

    @DBField(name = HpUser.ZIP_CODE)
    private String zipCode;

}
