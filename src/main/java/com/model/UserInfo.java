package com.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by king on 15/11/6.
 */
@Entity
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UserInfo {
    @Id
    @GeneratedValue
    public long accountid;
    public String username;
    public String password;
    @Column(unique=true,columnDefinition = "char",length=11)
    public String phone;
    @Column(length=2)
    public String sex;
    public String nickname;
    @Column(length=128)
    public String scene;
    @Column(length=128)
    public String constitution;
    public String avatar;
    @Column(length=14)
    public String birthday;
    public float height;
    public float weight;
    @Column(length=50)
    public String city;
}