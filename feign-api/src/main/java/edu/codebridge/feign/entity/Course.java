package edu.codebridge.feign.entity;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class Course {

    private Integer courseId;
    private String courseName;
    private String courseIntro;
    private Long userId;
    private  Integer modelId;
    private User user;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Short deleted;
    private List<Class> classes;


}
