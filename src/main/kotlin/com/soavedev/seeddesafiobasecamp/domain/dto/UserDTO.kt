package com.soavedev.seeddesafiobasecamp.domain.dto

import com.soavedev.seeddesafiobasecamp.domain.enums.UserRoles
import com.soavedev.seeddesafiobasecamp.domain.enums.UserStatus
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import java.util.UUID

data class UserDTO(

        var id: UUID?,

        @field:NotEmpty(message = "name must be informed")
        @field:Size(max = 50, message = "name must have max of 50 characters")
        var name: String,

        @field:Email(message = "email must be valid")
        @field:NotEmpty(message = "email must be informed")
        var emailAddress: String,

        var role: UserRoles,

        var status: UserStatus,

        var location: String,

        var shortBio: String,

        var profilePictureUrl: String
)
