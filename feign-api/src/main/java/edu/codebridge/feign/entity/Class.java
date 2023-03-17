package edu.codebridge.feign.entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
@Data
public class Class {
    private int classId;
    private int courseId;

    private Course course;
    private Long userId;
    private User user;
    private String className;
    private short  deleted;

}
