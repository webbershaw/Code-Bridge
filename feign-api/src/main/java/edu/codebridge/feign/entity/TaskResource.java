package edu.codebridge.feign.entity;

import lombok.Data;

import java.util.List;

@Data
public class TaskResource {
    private Integer taskId;
    private Integer resourceId;
    private List<Resource> resources;
    private Double score;
}
