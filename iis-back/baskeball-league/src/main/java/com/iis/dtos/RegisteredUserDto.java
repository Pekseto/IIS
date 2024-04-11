package com.iis.dtos;

import com.iis.model.PlayerStatus;
import com.iis.model.Role;
import com.iis.model.Team;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class RegisteredUserDto {

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

    private double height;

    private double weight;

    private String status;

    private Long team;
}
