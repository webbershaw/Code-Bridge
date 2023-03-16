package edu.codebridge.feign.entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
@Data
public class Class {
    private int classId;
    private int courseId;
    private Long UserId;
    private String className;
    private short  deleted;

}
