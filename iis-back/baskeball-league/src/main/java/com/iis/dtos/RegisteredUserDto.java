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
    private Long id;

    private String email;

    private String password;

    private String name;

    private String surname;

    private Date birthday;

    private String phoneNumber;

    private String city;

    private String country;

    private String role;

    private double height;

    private double weight;

    private String playerStatus;

    private Team team;
}
