package edu.codebridge.feign.entity;


import lombok.Data;

@Data
public class StudentTaskResource {
    private Long    userId;
    private Integer taskId;
    private Integer resourceId;
    private Integer   status;
    private Double  score;

    private String  answer;

}
