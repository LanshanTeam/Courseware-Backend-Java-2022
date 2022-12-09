package com.example.springbootlesson.bean;

import lombok.Data;

@Data
public class Result<T> {
    private T data;
    private String msg;
    private Integer code;
}
