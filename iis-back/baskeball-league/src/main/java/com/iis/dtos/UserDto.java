package com.iis.dtos;

import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String email;

    private String password;

    private String name;

    private String surname;

    private String birthday;

    private String phoneNumber;

    private String city;

    private String country;

    private String jmbg;

    private String role;
}
