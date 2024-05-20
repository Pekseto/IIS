package com.iis.util;

import com.iis.dtos.MatchEventDto;
import com.iis.dtos.RegisteredUserDto;
import com.iis.model.*;
import com.iis.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperImpl implements Mapper {

    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;

    public MapperImpl(ModelMapper modelMapper, TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public <T, U> U map(T source, Class<U> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    @Override
    public <T, U> Page<U> mapPage(Page<T> sourcePage, Class<U> targetClass) {
        List<U> content = mapList(sourcePage.getContent(), targetClass);
        return new PageImpl<>(content, sourcePage.getPageable(), sourcePage.getTotalElements());
    }

    @Override
    public <T, U> List<U> mapList(List<T> sourceList, Class<U> targetClass) {
        return sourceList.stream()
                .map(source -> map(source, targetClass))
                .collect(Collectors.toList());
    }

    public RegisteredUserDto mapTeamManagerToDto(TeamManager teamManager) {
        RegisteredUserDto dto = new RegisteredUserDto();
        dto.setId(teamManager.getId());
        dto.setEmail(teamManager.getEmail());
        dto.setPassword(teamManager.getPassword());
        dto.setName(teamManager.getName());
        dto.setSurname(teamManager.getSurname());
        dto.setBirthday(teamManager.getBirthday().toString());
        dto.setJmbg(teamManager.getJmbg());
        dto.setCity(teamManager.getCity());
        dto.setCountry(teamManager.getCountry());
        dto.setTeam(teamManager.getTeam().getId());
        dto.setPhoneNumber(teamManager.getPhoneNumber());
        return dto;
    }

    public TeamManager mapDtoToTeamManager(RegisteredUserDto dto) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDate birthdayDate = LocalDate.parse(dto.getBirthday(), formatter);
        java.util.Date birthday = java.util.Date.from(birthdayDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TeamManager teamManager = new TeamManager();
        teamManager.setId(dto.getId());
        teamManager.setEmail(dto.getEmail());
        teamManager.setPassword(dto.getPassword());
        teamManager.setName(dto.getName());
        teamManager.setSurname(dto.getSurname());
        teamManager.setBirthday(birthday);
        teamManager.setJmbg(dto.getJmbg());
        teamManager.setCity(dto.getCity());
        teamManager.setCountry(dto.getCountry());
        Team team = teamRepository.getById(dto.getTeam());
        teamManager.setTeam(team);
        teamManager.setPhoneNumber(dto.getPhoneNumber());
        return teamManager;
    }

    public RegisteredUserDto mapCoachToDto(Coach coach) {
        RegisteredUserDto dto = new RegisteredUserDto();
        dto.setId(coach.getId());
        dto.setEmail(coach.getEmail());
        dto.setPassword(coach.getPassword());
        dto.setName(coach.getName());
        dto.setSurname(coach.getSurname());
        dto.setBirthday(coach.getBirthday().toString());
        dto.setJmbg(coach.getJmbg());
        dto.setCity(coach.getCity());
        dto.setCountry(coach.getCountry());
        dto.setTeam(coach.getTeam().getId());
        dto.setPhoneNumber(coach.getPhoneNumber());
        return dto;
    }

    public Coach mapDtoToCoach(RegisteredUserDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDate birthdayDate = LocalDate.parse(dto.getBirthday(), formatter);
        java.util.Date birthday = java.util.Date.from(birthdayDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Coach coach = new Coach();
        coach.setId(dto.getId());
        coach.setEmail(dto.getEmail());
        coach.setPassword(dto.getPassword());
        coach.setName(dto.getName());
        coach.setSurname(dto.getSurname());
        coach.setBirthday(birthday); // Pretvoriti String u java.util.Date po potrebi
        coach.setJmbg(dto.getJmbg());
        coach.setCity(dto.getCity());
        coach.setCountry(dto.getCountry());
        Team team = teamRepository.getById(dto.getTeam());
        coach.setTeam(team);
        coach.setPhoneNumber(dto.getPhoneNumber());
        return coach;
    }

    @Override
    public MatchEventDto mapMatchEventToDto(MatchEvent matchEvent) {
        var matchEventDto = modelMapper.map(matchEvent, MatchEventDto.class);
        matchEventDto.setType(matchEvent.getType().getEventName());
        return matchEventDto;
    }

    public RegisteredUserDto mapPlayerToDto(Player player) {
        RegisteredUserDto dto = new RegisteredUserDto();
        dto.setId(player.getId());
        dto.setEmail(player.getEmail());
        dto.setPassword(player.getPassword());
        dto.setName(player.getName());
        dto.setSurname(player.getSurname());
        dto.setBirthday(player.getBirthday().toString());
        dto.setJmbg(player.getJmbg());
        dto.setCity(player.getCity());
        dto.setCountry(player.getCountry());
        dto.setTeam(player.getTeam().getId());
        dto.setPhoneNumber(player.getPhoneNumber());
        dto.setJerseyNumber((long) player.getJerseyNumber());
        dto.setWeight(player.getWeight());
        dto.setHeight(player.getHeight());
        dto.setStatus(player.getStatus().toString());
        return dto;
    }

    public Player mapDtoToPlayer(RegisteredUserDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDate birthdayDate = LocalDate.parse(dto.getBirthday(), formatter);
        java.util.Date birthday = java.util.Date.from(birthdayDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Player player = new Player();
        player.setId(dto.getId());
        player.setEmail(dto.getEmail());
        player.setPassword(dto.getPassword());
        player.setName(dto.getName());
        player.setSurname(dto.getSurname());
        player.setBirthday(birthday); // Pretvoriti String u java.util.Date po potrebi
        player.setJmbg(dto.getJmbg());
        player.setCity(dto.getCity());
        player.setCountry(dto.getCountry());
        Team team = teamRepository.getById(dto.getTeam());
        player.setTeam(team);
        player.setPhoneNumber(dto.getPhoneNumber());
        player.setJerseyNumber(Math.toIntExact(dto.getJerseyNumber()));
        player.setWeight(dto.getWeight());
        player.setHeight(dto.getHeight());
        player.setStatus(PlayerStatus.valueOf(dto.getStatus()));

        return player;
    }
}
