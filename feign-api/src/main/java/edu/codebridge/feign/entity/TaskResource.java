package edu.codebridge.feign.entity;

import lombok.Data;

@Data
public class TaskResource {
    private int taskId;
    private int  resourceId;
    private  double   score;
}
