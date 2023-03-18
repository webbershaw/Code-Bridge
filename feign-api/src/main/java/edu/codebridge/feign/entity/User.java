package edu.codebridge.feign.entity;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User {
    private Long id;
    private String username;
    private String  pwd;
    private String email;
    private Short deleted;
    private String avatarUrl;
    private Short identity;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String  intro;
    private Short certified;
    private Integer schoolId;
    private String personId;
    private String name;
    private String tel;

}
