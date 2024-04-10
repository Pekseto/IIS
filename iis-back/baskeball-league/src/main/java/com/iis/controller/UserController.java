package com.iis.controller;

import com.iis.dtos.RegisteredUserDto;
import com.iis.model.Coach;
import com.iis.model.Player;
import com.iis.model.TeamManager;
import com.iis.security.authentication.AuthenticationRequest;
import com.iis.security.authentication.AuthenticationResponse;
import com.iis.service.AuthenticationService;
import com.iis.service.UserService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final Mapper mapper;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisteredUserDto> register(@RequestBody RegisteredUserDto userForRegistration) {
        return switch (userForRegistration.getRole()) {
            case "PLAYER" -> ResponseEntity.ok(registerPlayer(userForRegistration));
            case "COACH" -> ResponseEntity.ok(registerCoach(userForRegistration));
            case "TEAM_MANAGER" -> ResponseEntity.ok(registerTeamManager(userForRegistration));
            default -> ResponseEntity.badRequest().build();
        };
    }

    private RegisteredUserDto registerPlayer(RegisteredUserDto userForRegistration) {
        userForRegistration.setPassword("player");
        Player newPlayer = mapper.map(userForRegistration, Player.class);
        userService.registerPlayer(newPlayer);

        return userForRegistration;
    }

    private RegisteredUserDto registerCoach(RegisteredUserDto userForRegistration) {
        userForRegistration.setPassword("coach");
        Coach newCoach = mapper.map(userForRegistration, Coach.class);
        userService.registerCoach(newCoach);

        return userForRegistration;
    }

    private RegisteredUserDto registerTeamManager(RegisteredUserDto userForRegistration) {
        userForRegistration.setPassword("manager");
        TeamManager newManager = mapper.map(userForRegistration, TeamManager.class);
        userService.registerTeamManager(newManager);

        return userForRegistration;
    }
}
