package edu.codebridge.feign.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class Resource {
    private Integer  resourceId;
    private Short   resourceType;
    private String  title;
    private String  content;
    private String  resourceUrl;
    private Short   deleted;
    private Integer classificationId;
    private List<Integer> taskIds;
    private  String correctAnswer;
    private String isCorrect;

}
