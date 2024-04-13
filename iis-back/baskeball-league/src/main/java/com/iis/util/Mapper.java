package com.iis.util;

import com.iis.dtos.RegisteredUserDto;
import com.iis.model.Coach;
import com.iis.model.Player;
import com.iis.model.TeamManager;
import org.springframework.data.domain.Page;

import java.util.List;

public interface Mapper {

    <T, U> U map(T source, Class<U> targetClass);

    <T, U> Page<U> mapPage(Page<T> sourcePage, Class<U> targetClass);

    <T, U> List<U> mapList(List<T> sourceList, Class<U> targetClass);

    public RegisteredUserDto mapTeamManagerToDto(TeamManager teamManager);
    public RegisteredUserDto mapPlayerToDto(Player player);
    public RegisteredUserDto mapCoachToDto(Coach coach);
    public Player mapDtoToPlayer(RegisteredUserDto dto);
    public TeamManager mapDtoToTeamManager(RegisteredUserDto dto);
    public Coach mapDtoToCoach(RegisteredUserDto dto);
}
