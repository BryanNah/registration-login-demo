package com.bryan.registrationlogindemo.service

import com.bryan.registrationlogindemo.dto.UserDto
import com.bryan.registrationlogindemo.entity.User

interface UserService {

    void saveUser(UserDto userDto)

    User findUserByEmail(String email)

    List<UserDto> findAllUsers()
}