package com.example.check.controllers;


import com.example.check.models.User;
import com.example.check.models.UserCredentials;
import com.example.check.repositorys.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Before
    public void setUp() {
        User user = new User();
        user.setName("sotos");
        user.setEmail("yolo@hotmail.com");
        user.setUsername("kele");
        user.setPassword(passwordEncoder.encode("12341234"));

        userRepository.save(user);
    }

 //register  endpoint
    @Test
    public void successfullSignUp() throws Exception {
        User resource = new User();
        resource.setName("John");
        resource.setEmail("Doe@hotmail.com");
        resource.setUsername("john");
        resource.setPassword("12341234");


        String json = objectMapper.writeValueAsString(resource);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.token",notNullValue()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void SignUpWithEmptyName() throws Exception {
        User resource = new User();
        resource.setName("");
        resource.setEmail("Doe@hotmail.com");
        resource.setUsername("john1");
        resource.setPassword("12341234");


        String json = objectMapper.writeValueAsString(resource);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void SignUpWithEmptyEmailI() throws Exception {
        User resource = new User();
        resource.setName("John");
        resource.setEmail("");
        resource.setUsername("john1");
        resource.setPassword("12341234");


        String json = objectMapper.writeValueAsString(resource);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.details[0]",is("email must not be empty")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void SignUpWithEmptyUsername() throws Exception {
        User resource = new User();
        resource.setName("John");
        resource.setEmail("Doe@hotmail.com");
        resource.setUsername("");
        resource.setPassword("12341234");


        String json = objectMapper.writeValueAsString(resource);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.details[0]",is("username must not be empty")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void SignUpWithEmptyPassword() throws Exception {
        User resource = new User();
        resource.setName("John");
        resource.setEmail("Doe@hotmail.com");
        resource.setUsername("john1");
        resource.setPassword("");


        String json = objectMapper.writeValueAsString(resource);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.details[0]",is("password must not be empty")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void signUpWithExistingUsername() throws Exception {
        User resource = new User();
        resource.setName("John");
        resource.setEmail("Doe@hotmail.com");
        resource.setUsername("kele");
        resource.setPassword("12341234");


        String json = objectMapper.writeValueAsString(resource);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andDo(MockMvcResultHandlers.print());
    }

    //login endpoint

    @Test
    public void successfullSignIn() throws Exception {
        UserCredentials userCredentials = new UserCredentials("kele", "12341234");


        String json = objectMapper.writeValueAsString(userCredentials);

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.token",notNullValue()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void SignInWithInvalidUsername() throws Exception {
        UserCredentials userCredentials = new UserCredentials("invalidUsername", "12341234");


        String json = objectMapper.writeValueAsString(userCredentials);

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void SignInWithInvalidPassword() throws Exception {
        UserCredentials userCredentials = new UserCredentials("kele", "invalidPassword");


        String json = objectMapper.writeValueAsString(userCredentials);

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print());
    }


    @After
    public void deleteUsers() {
        userRepository.deleteAll();

    }


}
