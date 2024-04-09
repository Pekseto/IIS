package com.iis.model;

import com.iis.security.authorization.Permission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {

    LEAGUE_ADMIN(Set.of(
            Permission.LEAGUE_ADMIN_READ,
            Permission.LEAGUE_ADMIN_UPDATE,
            Permission.LEAGUE_ADMIN_DELETE,
            Permission.LEAGUE_ADMIN_CREATE
    )),
    PLAYER(Set.of(
            Permission.PLAYER_READ,
            Permission.PLAYER_UPDATE
    )),
    COACH(Set.of(
            Permission.COACH_READ,
            Permission.COACH_UPDATE
    )),
    TEAM_MANAGER(Set.of(
            Permission.TEAM_MANAGER_READ,
            Permission.TEAM_MANAGER_UPDATE
    ))
    ;

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}