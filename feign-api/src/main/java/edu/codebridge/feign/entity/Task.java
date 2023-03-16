package edu.codebridge.feign.entity;

import lombok.Data;

@Data
public class Task {
    private  int taskId;
    private int courseId;
    private String  taskName;
    private short taskType;
    private short deleted;

}