package edu.codebridge.feign.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class School implements Serializable {
    private Integer schoolId;
    private String imagePath;
    private String schoolName;
    private String schoolIntro;

}
