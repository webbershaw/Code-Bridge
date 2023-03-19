package edu.codebridge.feign.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClassTask {
    private Integer classId;
    private Integer taskId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Short invisible;
    private Short accessible;
    private Double weight;
    private Integer resubmit;
    private Short checkAfterSubmit;
    private Short correctionMode;
    private Short deleted;

}
