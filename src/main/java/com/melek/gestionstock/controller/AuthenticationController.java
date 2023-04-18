package com.melek.gestionstock.controller;

import com.melek.gestionstock.dto.auth.AuthenticationRequest;
import com.melek.gestionstock.dto.auth.AuthenticationResponse;
import com.melek.gestionstock.model.auth.ExtendedUser;
import com.melek.gestionstock.service.auth.ApplicationUserDetailsService;
import com.melek.gestionstock.utils.JwtUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.melek.gestionstock.utils.Constants.AUTHENTICATION_ROOT;

@RestController
@RequestMapping(AUTHENTICATION_ROOT)
@Api
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  request.getLogin(), request.getPassword()
          )
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());
        final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails);
        return ResponseEntity.ok(AuthenticationResponse.builder()
                        .accessToken(jwt)
                .build());
    }
}
