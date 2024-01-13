package com.soavedev.seeddesafiobasecamp.model.entity

import com.soavedev.seeddesafiobasecamp.model.enums.UserRoles
import com.soavedev.seeddesafiobasecamp.model.enums.UserStatus
import java.util.UUID

data class User(
        var id: UUID,
        var name: String,
        var emailAddress: String,
        var role: UserRoles,
        var status: UserStatus,
        var location: String,
        var shortBio: String,
        var profilePictureUrl: String
)
