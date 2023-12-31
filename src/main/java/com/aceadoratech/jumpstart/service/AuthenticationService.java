package com.aceadoratech.jumpstart.service;
import com.aceadoratech.jumpstart.config.JwtService;
import com.aceadoratech.jumpstart.entity.Roles;
import com.aceadoratech.jumpstart.entity.UserDetail;
import com.aceadoratech.jumpstart.entity.UserLogin;
import com.aceadoratech.jumpstart.exchanges.AuthenticationRequest;
import com.aceadoratech.jumpstart.exchanges.AuthenticationResponse;
import com.aceadoratech.jumpstart.exchanges.RegisterRequest;
import com.aceadoratech.jumpstart.repository.UserDetailRepository;
import com.aceadoratech.jumpstart.repository.UserLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserLoginRepository userLoginRepository;
    private final UserDetailRepository userDetailRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = UserLogin.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Roles.CUSTOMER)
                .build();
        userLoginRepository.save(user);
        var detail = UserDetail.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .age(0)
                .address("Haven't Decided")
                .gender("Haven't Decided")
                .user(userLoginRepository.findByEmail(registerRequest.getEmail()).get())
                .build();
        userDetailRepository.save(detail);
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword())
        );
        var user = userLoginRepository.findByEmail(authRequest.getEmail()).orElseThrow();
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }
}
