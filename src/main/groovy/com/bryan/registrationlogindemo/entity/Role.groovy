package com.bryan.registrationlogindemo.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import lombok.Builder

@Builder
@Entity
@Table(name="roles")
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(nullable=false, unique=true)
    String name

    @ManyToMany(mappedBy="roles")
    List<User> users
}
