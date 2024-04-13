package com.iis.service.impl;

import com.iis.dtos.RegisteredUserDto;
import com.iis.model.Player;
import com.iis.model.TeamManager;
import com.iis.repository.TeamManagerRepository;
import com.iis.repository.TeamRepository;
import com.iis.service.TeamManagerService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TeamManagerServiceImpl implements TeamManagerService {

    private final TeamManagerRepository teamManagerRepository;
    private final Mapper mapper;
    private final TeamRepository teamRepository;
    @Override
    public RegisteredUserDto edit(RegisteredUserDto manager) {
        TeamManager managerToEdit = teamManagerRepository.getById(manager.getId());//mapper.map(user,User.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!manager.getPassword().equals(managerToEdit.getPassword())) {
            String hashedPassword = encoder.encode(manager.getPassword());
            managerToEdit.setPassword(hashedPassword);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDate birthdayDate = LocalDate.parse(manager.getBirthday(), formatter);
        Date birthday = Date.from(birthdayDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        managerToEdit.setBirthday(birthday);
        managerToEdit.setCity(manager.getCity());
        managerToEdit.setCountry(manager.getCountry());
        managerToEdit.setJmbg(manager.getJmbg());
        managerToEdit.setEmail(manager.getEmail());
        managerToEdit.setPhoneNumber(manager.getPhoneNumber());
        managerToEdit.setSurname(manager.getSurname());
        managerToEdit.setName(manager.getName());
        if(manager.getTeam() != -1)  managerToEdit.setTeam(teamRepository.getById(manager.getTeam()));

        return mapper.mapTeamManagerToDto(teamManagerRepository.save(managerToEdit));
    }

    @Override
    public TeamManager getById(long id) {
        return teamManagerRepository.getById(id);
    }
}
