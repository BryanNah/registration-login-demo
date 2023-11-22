package com.bryan.registrationlogindemo.security

import com.bryan.registrationlogindemo.entity.Role
import com.bryan.registrationlogindemo.entity.User
import com.bryan.registrationlogindemo.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository

    CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.email,
                    user.password,
                    mapRolesToAuthorities(user.getRoles()))
        }else{
            throw new UsernameNotFoundException("Invalid username or password.")
        }
    }

    static Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList())
        return mapRoles
    }
}
