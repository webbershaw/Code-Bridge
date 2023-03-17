package edu.codebridge.feign.entity;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class Course {



private int courseId;
private String courseName;
private String courseIntro;
private Long user_id;

private  Integer model_id;

private  Model model;
private User user;

private LocalDateTime   startTime;
private LocalDateTime endTime;
private short deleted;

}
