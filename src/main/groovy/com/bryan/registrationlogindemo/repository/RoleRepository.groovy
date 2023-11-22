package com.bryan.registrationlogindemo.repository

import com.bryan.registrationlogindemo.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name)
}