package edu.codebridge.feign.entity;


import lombok.Data;

@Data
public class StudentTask {
    private Long   userId;
    private Integer taskId;
    private Short  status;
    private Double score;
    private Short  accessible;

}
