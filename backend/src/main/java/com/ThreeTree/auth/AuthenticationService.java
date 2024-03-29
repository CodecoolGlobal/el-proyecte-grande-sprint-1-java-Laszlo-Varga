package com.ThreeTree.auth;

import com.ThreeTree.config.JwtService;
import com.ThreeTree.dao.PersonRepository;
import com.ThreeTree.model.Person;
import com.ThreeTree.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PersonRepository repository;
    private final PasswordEncoder passwordEncoder; // TODO: has to be final to be picked up by constructor
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) throws InvalidObjectException {

        if (emailAlreadyExists(request.getEmail())) {
            throw new InvalidObjectException("Email address already registered");
        }

        var person = Person.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .password(passwordEncoder.encode(request.getPassword()))//Bcrypt algorithm
                .role((Role.USER))
                .build();

        repository.save(person);
        var jwtToken = jwtService.generateToken(person);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private boolean emailAlreadyExists(String email) {
        Optional<Person> existingPerson = repository.findByEmail(email);
        return existingPerson.isPresent();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var person = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(person);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();


    }
}
