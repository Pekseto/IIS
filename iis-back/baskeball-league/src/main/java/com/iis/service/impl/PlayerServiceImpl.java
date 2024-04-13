package com.iis.service.impl;

import com.iis.dtos.RegisteredUserDto;
import com.iis.model.Player;
import com.iis.model.User;
import com.iis.repository.PlayerRepository;
import com.iis.repository.TeamRepository;
import com.iis.service.PlayerService;
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
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final Mapper mapper;
    private final TeamRepository teamRepository;
    @Override
    public RegisteredUserDto edit(RegisteredUserDto player) {
        Player playerToEdit = playerRepository.getById(player.getId());//mapper.map(user,User.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!player.getPassword().equals(playerToEdit.getPassword())) {
            String hashedPassword = encoder.encode(player.getPassword());
            playerToEdit.setPassword(hashedPassword);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDate birthdayDate = LocalDate.parse(player.getBirthday(), formatter);
        Date birthday = Date.from(birthdayDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        playerToEdit.setBirthday(birthday);
        playerToEdit.setCity(player.getCity());
        playerToEdit.setCountry(player.getCountry());
        playerToEdit.setJmbg(player.getJmbg());
        playerToEdit.setEmail(player.getEmail());
        playerToEdit.setPhoneNumber(player.getPhoneNumber());
        playerToEdit.setSurname(player.getSurname());
        playerToEdit.setName(player.getName());
        playerToEdit.setJerseyNumber(1);
        playerToEdit.setHeight(player.getHeight());
        playerToEdit.setWeight(player.getWeight());
        playerToEdit.setJerseyNumber(Math.toIntExact(player.getJerseyNumber()));
        playerToEdit.setTeam(teamRepository.getById(player.getTeam()));

        return mapper.map(playerRepository.save(playerToEdit), RegisteredUserDto.class);
    }

    @Override
    public Player getById(long id) {
        return playerRepository.getReferenceById(id);
    }
}
