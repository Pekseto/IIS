package com.iis.controller;

import com.iis.dtos.RegisteredUserDto;
import com.iis.model.Coach;
import com.iis.model.Player;
import com.iis.model.TeamManager;
import com.iis.security.authentication.AuthenticationRequest;
import com.iis.security.authentication.AuthenticationResponse;
import com.iis.service.AuthenticationService;
import com.iis.service.TeamService;
import com.iis.service.UserService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final TeamService teamService;
    private final Mapper mapper;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_LEAGUE_ADMIN')")
    public ResponseEntity<RegisteredUserDto> register(@RequestBody RegisteredUserDto userForRegistration) {
        return switch (userForRegistration.getRole()) {
            case "PLAYER" -> ResponseEntity.ok(registerPlayer(userForRegistration));
            case "COACH" -> ResponseEntity.ok(registerCoach(userForRegistration));
            case "TEAM_MANAGER" -> ResponseEntity.ok(registerTeamManager(userForRegistration));
            default -> ResponseEntity.badRequest().build();
        };
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('ROLE_LEAGUE_ADMIN') and #admin.id == authentication.principal.id")
    public ResponseEntity<RegisteredUserDto> updateAdmin(@RequestBody RegisteredUserDto admin){
        try {
            return ResponseEntity.ok(userService.updateAdmin(admin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/getAdmin/{id}")
    @PreAuthorize("hasRole('ROLE_LEAGUE_ADMIN')")
    public ResponseEntity<RegisteredUserDto> getById(@PathVariable long id){
        return ResponseEntity.ok(mapper.map(userService.getById(id),RegisteredUserDto.class));
    }

    private RegisteredUserDto registerPlayer(RegisteredUserDto userForRegistration) {
        userForRegistration.setPassword("player");
        Player newPlayer = mapper.map(userForRegistration, Player.class);
        newPlayer.setTeam(teamService.getById(userForRegistration.getTeam()));
        userService.registerPlayer(newPlayer);

        return userForRegistration;
    }

    private RegisteredUserDto registerCoach(RegisteredUserDto userForRegistration) {
        userForRegistration.setPassword("coach");
        Coach newCoach = mapper.map(userForRegistration, Coach.class);
        newCoach.setTeam(teamService.getById(userForRegistration.getTeam()));
        userService.registerCoach(newCoach);

        return userForRegistration;
    }

    private RegisteredUserDto registerTeamManager(RegisteredUserDto userForRegistration) {
        userForRegistration.setPassword("manager");
        TeamManager newManager = mapper.map(userForRegistration, TeamManager.class);
        newManager.setTeam(teamService.getById(userForRegistration.getTeam()));
        userService.registerTeamManager(newManager);

        return userForRegistration;
    }
}
