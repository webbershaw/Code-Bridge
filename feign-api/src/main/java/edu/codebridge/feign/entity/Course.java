package edu.codebridge.feign.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class Course {



private int courseId;
private String courseName;
private String courseIntro;
private LocalDateTime   startTime;
private LocalDateTime endTime;
private short deleted;

}
