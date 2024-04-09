package com.iis.model;

import com.iis.security.token.JwtToken;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Long id;

    @Column(name="email", unique = true, nullable = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="common_name", nullable = false)
    private String commonName;

    @Column(name="given_name", nullable = false)
    private String givenName;

    @Column(name="organization", nullable = false)
    private String organization;

    @Column(name="organizational_unit", nullable = false)
    private String organizationalUnit;

    @Column(name="country", nullable = false)
    private String country;

    @Column(name="locality", nullable = false)
    private String locality;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<JwtToken> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole().getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
