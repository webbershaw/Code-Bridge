package edu.codebridge.feign.entity;


import lombok.Data;

@Data
public class Result {
    private Integer code;
    private Object data;
    private String msg;
}
