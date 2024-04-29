package com.iis.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordKeeperDto {
    private long id;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    private String confirmPassword;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String surname;

    @NotNull
    private String birthday;

    @NotNull
    private String phoneNumber;

    @NotNull
    @NotBlank
    private String city;

    @NotNull
    @NotBlank
    private String country;

    @NotNull
    private String jmbg;

    private String role;
}
