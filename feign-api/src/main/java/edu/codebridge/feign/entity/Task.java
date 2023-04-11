package edu.codebridge.feign.entity;

import lombok.Data;

import java.util.List;

@Data
public class Task {
    private Integer taskId;
    private Integer modelId;
    private Course  course;
    private String  taskName;
    private Short   taskType;
    private Short   deleted;
    private List<Integer> resourceIds;
    private List<Resource> resources;

}