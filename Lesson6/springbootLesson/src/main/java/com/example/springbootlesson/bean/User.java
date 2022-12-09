package com.example.springbootlesson.bean;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String gender;
    private String homeAddr;

}
