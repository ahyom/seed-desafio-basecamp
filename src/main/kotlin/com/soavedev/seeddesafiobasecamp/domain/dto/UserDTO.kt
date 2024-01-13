package com.soavedev.seeddesafiobasecamp.domain.dto

import com.soavedev.seeddesafiobasecamp.domain.enums.UserRoles
import com.soavedev.seeddesafiobasecamp.domain.enums.UserStatus
import java.util.UUID

data class UserDTO(

        var id: UUID?,


        var name: String,

        var emailAddress: String,

        var role: UserRoles,

        var status: UserStatus,

        var location: String,

        var shortBio: String,

        var profilePictureUrl: String
)
