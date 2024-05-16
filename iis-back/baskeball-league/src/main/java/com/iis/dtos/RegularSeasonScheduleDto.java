package com.iis.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RegularSeasonScheduleDto {
    private Long id;
    private List<MatchDto> matches;
    private Date seasonStart;
    private Date seasonEnd;
}
