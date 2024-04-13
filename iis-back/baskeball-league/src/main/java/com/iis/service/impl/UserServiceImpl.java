package com.iis.service.impl;

import com.iis.dtos.RegisteredUserDto;
import com.iis.model.Coach;
import com.iis.model.Player;
import com.iis.model.TeamManager;
import com.iis.model.User;
import com.iis.repository.CoachRepository;
import com.iis.repository.PlayerRepository;
import com.iis.repository.TeamManagerRepository;
import com.iis.repository.UserRepository;
import com.iis.service.UserService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PlayerRepository playerRepository;
    private final CoachRepository coachRepository;
    private final TeamManagerRepository teamManagerRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;
    @Override
    public Player registerPlayer(Player player) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        player.setPassword(encoder.encode(player.getPassword()));

        return playerRepository.save(player);
    }

    @Override
    public Coach registerCoach(Coach coach) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        coach.setPassword(encoder.encode(coach.getPassword()));

        return coachRepository.save(coach);
    }

    @Override
    public TeamManager registerTeamManager(TeamManager teamManager) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        teamManager.setPassword(encoder.encode(teamManager.getPassword()));

        return teamManagerRepository.save(teamManager);
    }

    @Override
    public RegisteredUserDto updateAdmin(RegisteredUserDto user) {
        User adminToEdit = userRepository.getById(user.getId());//mapper.map(user,User.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!user.getPassword().equals(adminToEdit.getPassword())) {
            String hashedPassword = encoder.encode(user.getPassword());
            adminToEdit.setPassword(hashedPassword);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDate birthdayDate = LocalDate.parse(user.getBirthday(), formatter);
        Date birthday = Date.from(birthdayDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        adminToEdit.setBirthday(birthday);
        adminToEdit.setCity(user.getCity());
        adminToEdit.setCountry(user.getCountry());
        adminToEdit.setJmbg(user.getJmbg());
        adminToEdit.setEmail(user.getEmail());
        adminToEdit.setPhoneNumber(user.getPhoneNumber());
        adminToEdit.setSurname(user.getSurname());
        adminToEdit.setName(user.getName());


        return mapper.map(userRepository.save(adminToEdit),RegisteredUserDto.class);
    }

    @Override
    public User getById(long id) {
        return userRepository.getReferenceById(id);
    }

}
