package com.iis.service.impl;

import com.iis.dtos.RegisteredUserDto;
import com.iis.model.Coach;
import com.iis.model.Player;
import com.iis.repository.CoachRepository;
import com.iis.repository.TeamRepository;
import com.iis.service.CoachService;
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
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;
    private final Mapper mapper;
    private final TeamRepository teamRepository;

    @Override
    public RegisteredUserDto edit(RegisteredUserDto coach) {
        Coach coachToEdit = coachRepository.getById(coach.getId());//mapper.map(user,User.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!coach.getPassword().equals(coachToEdit.getPassword())) {
            String hashedPassword = encoder.encode(coach.getPassword());
            coachToEdit.setPassword(hashedPassword);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDate birthdayDate = LocalDate.parse(coach.getBirthday(), formatter);
        Date birthday = Date.from(birthdayDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        coachToEdit.setBirthday(birthday);
        coachToEdit.setCity(coach.getCity());
        coachToEdit.setCountry(coach.getCountry());
        coachToEdit.setJmbg(coach.getJmbg());
        coachToEdit.setEmail(coach.getEmail());
        coachToEdit.setPhoneNumber(coach.getPhoneNumber());
        coachToEdit.setSurname(coach.getSurname());
        coachToEdit.setName(coach.getName());
        coachToEdit.setTeam(teamRepository.getById(coach.getTeam()));

        return mapper.mapCoachToDto(coachRepository.save(coachToEdit));
    }

    @Override
    public Coach getById(long id) {
        return coachRepository.getById(id);
    }
}
