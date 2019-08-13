package com.example.check.services;


import com.example.check.errors.UsernameAlreadyExistsException;
import com.example.check.models.Book;
import com.example.check.models.User;
import com.example.check.repositorys.UserRepository;

import com.example.check.security.IAuthenticationFacade;
import com.example.check.security.JwtTokenUtil;
import com.example.check.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    IAuthenticationFacade authenticationFacade;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    public String login(String username,String password) throws Exception    {

            //TODO  2 authenticate methods
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        UserDetails userDetails=userDetailsServiceImpl.loadUserByUsername(username);

        return jwtTokenUtil.generateToken(userDetails);
    }







    public String register(User user) throws Exception {
        if(userRepository.existsByUsername(user.getUsername())){
            throw new UsernameAlreadyExistsException("Username "+user.getUsername()+" already exists");
        }
        String rawPassword=user.getPassword();
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return this.login(user.getUsername(),rawPassword);
    }


    public ResponseEntity changePassword(String password, String oldPassword) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user= userRepository.findByUsername(authentication.getName());

        if (bcryptEncoder.matches(oldPassword,user.getPassword())) {
            user.setPassword(bcryptEncoder.encode(password));
            userRepository.save(user);

            return  new ResponseEntity(HttpStatus.OK);
        }

        return  new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    public List<Book> getUserBooks() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user= userRepository.findByUsername(authentication.getName());

        return user.getLibrary();
    }


}
