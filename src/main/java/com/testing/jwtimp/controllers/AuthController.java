package com.testing.jwtimp.controllers;

import com.testing.jwtimp.model.AuthResponse;
import com.testing.jwtimp.model.Login;
import com.testing.jwtimp.model.Register;
import com.testing.jwtimp.persist.RolesEntity;
import com.testing.jwtimp.persist.UsersApiEntity;
import com.testing.jwtimp.repository.RolesRepository;
import com.testing.jwtimp.repository.UsersApiRepository;
import com.testing.jwtimp.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final UsersApiRepository usuariosRepository;
    private final JwtGenerator jwtGenerator;

    @Autowired

    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RolesRepository rolesRepository, UsersApiRepository usuariosRepository, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.usuariosRepository = usuariosRepository;
        this.jwtGenerator = jwtGenerator;
    }
    //Método para poder registrar usuarios con role "user"
    @PostMapping("register")
    public ResponseEntity<String> registrar(@RequestBody Register register) {
        if (usuariosRepository.existsByUsername(register.getUsername())) {
            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }
        UsersApiEntity usersApiEntity = new UsersApiEntity();
        usersApiEntity.setUsername(register.getUsername());
        usersApiEntity.setPassword(passwordEncoder.encode(register.getPassword()));
        RolesEntity rolesEntity = rolesRepository.findByName("USER").get();
        usersApiEntity.setRoles(Collections.singletonList(rolesEntity));
        usuariosRepository.save(usersApiEntity);
        return new ResponseEntity<>("Registro de usuario exitoso", HttpStatus.OK);
    }

    //Método para poder guardar usuarios de tipo ADMIN
    @PostMapping("registerAdm")
    public ResponseEntity<String> registrarAdmin(@RequestBody Register register) {
        if (usuariosRepository.existsByUsername(register.getUsername())) {
            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }
        UsersApiEntity usersApiEntity = new UsersApiEntity();
        usersApiEntity.setUsername(register.getUsername());
        usersApiEntity.setPassword(passwordEncoder.encode(register.getPassword()));
        RolesEntity rolesEntity = rolesRepository.findByName("ADMIN").get();
        usersApiEntity.setRoles(Collections.singletonList(rolesEntity));
        usuariosRepository.save(usersApiEntity);
        return new ResponseEntity<>("Registro de admin exitoso", HttpStatus.OK);
    }

    //Método para poder logear un usuario y obtener un token
    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody Login login) {
        System.out.println("Enter Username: "+ login.getUsername());
        System.out.println("Enter Password: "+ login.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                login.getUsername(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generarToken(authentication);
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }
}
