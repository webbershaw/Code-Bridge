package edu.codebridge.feign.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Model {


    private int modelId;
    private String modelName;
    private short isPublic;
    private LocalDateTime createdTime;
    private short deleted;
    private int  classificationId;



}
