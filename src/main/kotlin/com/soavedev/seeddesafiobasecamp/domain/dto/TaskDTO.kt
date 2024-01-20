package com.soavedev.seeddesafiobasecamp.domain.dto

import com.soavedev.seeddesafiobasecamp.domain.enums.TaskStatus
import jakarta.validation.Validation
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import java.time.LocalDateTime
import java.util.*

data class TaskDTO(
        var id: UUID?,

        @field:NotEmpty(message = "name must be informed")
        @field:Size(max = 100, message = "name must have max of 100 characters")
        var name: String,

        var startDate: LocalDateTime?,

        var finishDate: LocalDateTime?,

        var status: TaskStatus,

        var notes: String?,

        var bucketId: UUID,

        var groupId: UUID?,

        var userAssignId: UUID?,

        var userNotifyId: UUID?,
    ){

    fun validate(): List<String> {
        val validator = Validation.buildDefaultValidatorFactory().validator
        return validator.validate(this).map { it.message }
    }
}
