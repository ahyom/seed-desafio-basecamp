package com.soavedev.seeddesafiobasecamp.model.entity

import com.soavedev.seeddesafiobasecamp.model.enums.UserRoles
import com.soavedev.seeddesafiobasecamp.model.enums.UserStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "tb_user")
data class User(
        @Id
        var id: UUID,

        @Column(nullable = false)
        var name: String,

        @Column(nullable = false, unique = true)
        var emailAddress: String,

        @Column(nullable = false)
        var role: String,

        @Column(nullable = false)
        var status: String,

        var location: String,

        var shortBio: String,

        var profilePictureUrl: String
)
