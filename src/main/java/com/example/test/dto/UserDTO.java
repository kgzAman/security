package com.example.test.dto;

import lombok.Data;

@Data

public class UserDTO {

    private String name;

    private String address;

    private String email;

    private String password;

    public UserDTO(String name, String address, String email, String password) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
    }
}
