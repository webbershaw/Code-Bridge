package edu.codebridge.feign.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Resource {
    private Integer resourceId;
    private Short resourceType;
    private String title;
    private String content;
    private String resourceUrl;
    private Short deleted;
    private Integer classificationId;

}
