package edu.codebridge.feign.entity;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User {
    private Long id;
    private String userName;
    private String  pwd;
    private String email;
    private short deleted;
    private  String avatarUrl;
    private short identity;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private  String  intro;
    private short certified;
    private int schoolId;
    private int personId;
    private String name;
    private String tel;

}
