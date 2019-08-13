package com.example.check.converters;

import com.example.check.dtos.UserDTO;
import com.example.check.models.User;
import org.springframework.core.convert.converter.Converter;

public class UserToUserDtoConverter implements Converter<User,UserDTO> {


    public UserToUserDtoConverter() {
    }

    @Override
    public UserDTO convert(User user) {

        UserDTO userDTO=new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setRole(user.getRole());
        return userDTO;
    }


}
