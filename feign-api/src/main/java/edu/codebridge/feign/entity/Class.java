package edu.codebridge.feign.entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
@Data
public class Class {
    private Integer classId;
    private Integer courseId;
    private Long userId;
    private User user;
    private String className;
    private Short  deleted;
    private Course course;

}
