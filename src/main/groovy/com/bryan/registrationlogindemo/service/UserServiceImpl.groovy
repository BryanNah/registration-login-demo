package com.bryan.registrationlogindemo.service

import com.bryan.registrationlogindemo.dto.UserDto
import com.bryan.registrationlogindemo.entity.Role
import com.bryan.registrationlogindemo.entity.User
import com.bryan.registrationlogindemo.repository.RoleRepository
import com.bryan.registrationlogindemo.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class UserServiceImpl implements UserService {

    UserRepository userRepository
    RoleRepository roleRepository
    PasswordEncoder passwordEncoder

    UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository
        this.roleRepository = roleRepository
        this.passwordEncoder = passwordEncoder
    }

    @Override
    void saveUser(UserDto userDto) {
        User user = new User()
        user.setName(userDto.firstName + " " + userDto.lastName)
        user.setEmail(userDto.email)
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.password))

        Role role = roleRepository.findByName("ROLE_ADMIN")
        if(role == null){
            role = checkRoleExist()
        }
        user.setRoles(Arrays.asList(role))
        userRepository.save(user)
    }

    @Override
    User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
    }

    @Override
    List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll()
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList())
    }

    static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto()
        String[] str = user.name.split(" ")
        userDto.firstName = str[0]
        userDto.lastName = str[1]
        userDto.email = user.email
        return userDto
    }

    Role checkRoleExist(){
        Role role = new Role()
        role.name = "ROLE_ADMIN"
        return roleRepository.save(role)
    }
}
