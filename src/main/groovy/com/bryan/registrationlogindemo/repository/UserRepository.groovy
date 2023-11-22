package com.bryan.registrationlogindemo.repository

import com.bryan.registrationlogindemo.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email)
}
