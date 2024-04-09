package com.iis.service;

import com.iis.security.authentication.AuthenticationRequest;
import com.iis.security.authentication.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
