package edu.codebridge.feign.entity;


import lombok.Data;

@Data
public class Result {
    private Integer code;
    private Object data;
    private String msg;

    public Result(Integer code, Object data) {
        this.data = data;
        this.code = code;
    }

    public Result(Integer code, Object data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
