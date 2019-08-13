package com.example.check.controllers;

import com.example.check.models.User;
import com.example.check.models.UserCredentials;
import com.example.check.security.JwtAuthenticationResponse;
import com.example.check.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserCredentials authenticationRequest) throws Exception {

        String token= userService.login(authenticationRequest.getUsername(),authenticationRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }



    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user)  throws  Exception {
        String token=userService.register(user);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }




}
