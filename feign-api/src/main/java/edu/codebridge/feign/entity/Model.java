package edu.codebridge.feign.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.codebridge.feign.client.UserClient;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Model {


    private Integer modelId;
    private String modelName;
    private Short isPublic;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdTime;
    private Short deleted;
    private Integer  classificationId;
    private User user;
    private Long userId;

}
