package edu.codebridge.feign.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User implements Serializable {
    private Long id;
    private String username;
    private String  pwd;
    private String email;
    private Short deleted;
    private String avatarUrl;
    private Short identity;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime updateTime;
    private String  intro;
    private Short certified;
    private Integer schoolId;
    private String personId;
    private String name;
    private String tel;

    private String schoolName;

    private Integer verifyCode;
    private School school;

}
