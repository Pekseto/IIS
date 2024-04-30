package com.iis.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlayerDto extends UserDto {
    private double height;

    private double weight;

    private String status;

    private Long jerseyNumber;

    private Long teamId;
}
