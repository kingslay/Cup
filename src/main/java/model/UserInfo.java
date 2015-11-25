package model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by king on 15/11/6.
 */
//@Entity
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UserInfo {
//    @Id
//    @GeneratedValue
    public Integer accountid;
    public String username;
    public String password;
    public String phone;
    public String sex;
    public String nickname;
    public String scene;
    public String constitution;
    public String avatar;
    public String birthday;
    public float height;
    public float weight;
    public String city;
}