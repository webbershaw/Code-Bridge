package edu.codebridge.feign.entity;

import lombok.Data;

@Data
public class Task {
    private Integer taskId;
    private Integer courseId;

    private Course  course;

    private String  taskName;
    private Short   taskType;
    private Short   deleted;

}